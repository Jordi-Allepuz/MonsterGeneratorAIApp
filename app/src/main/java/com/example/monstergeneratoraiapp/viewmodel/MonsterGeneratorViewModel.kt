package com.example.monstergeneratoraiapp.viewmodel


import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.aallam.openai.api.BetaOpenAI
import com.aallam.openai.api.audio.TranscriptionRequest
import com.aallam.openai.api.chat.ChatCompletionRequest
import com.aallam.openai.api.chat.ChatMessage
import com.aallam.openai.api.chat.ChatRole
import com.aallam.openai.api.file.FileSource
import com.aallam.openai.api.image.ImageCreation
import com.aallam.openai.api.image.ImageEdit
import com.aallam.openai.api.image.ImageSize
import com.aallam.openai.api.image.ImageURL
import com.aallam.openai.api.logging.LogLevel
import com.aallam.openai.api.model.ModelId
import com.aallam.openai.client.LoggingConfig
import com.aallam.openai.client.OpenAI
import com.example.monstergeneratoraiapp.R
import com.example.monstergeneratoraiapp.audiorecorder.AudioRecorder
import com.example.monstergeneratoraiapp.config.ApiKey
import com.example.monstergeneratoraiapp.config.Config
import kotlinx.coroutines.launch
import okio.source
import java.io.File


class MonsterGeneratorViewModel : ViewModel() {

    private var openAI =
        OpenAI(token = ApiKey.OPENAI_API_KEY, logging = LoggingConfig(LogLevel.All))

    var info: String by mutableStateOf("")

    var loading: Boolean by mutableStateOf(false)
    var recording: Boolean by mutableStateOf(false)

    private var recorder: AudioRecorder? = null
    private var audioFile: File? = null


    //Audio

    fun recordAudio(context: Context) {

        if (recording) {
            recording = false
            recorder?.stopRecord()
            loadInfo(audioFile)
        } else {
            if (recorder == null) {
                recorder = AudioRecorder(context)
            }
        }
        File(context.cacheDir, Config.AUDIO_FILE).also {
            recorder?.startRecord(it)
            audioFile = it
            recording = true
        }
    }

    //Resumen

    @OptIn(BetaOpenAI::class)
    fun createInfoSummary() = viewModelScope.launch {

        startLoading()

        val chatCompletionRequest = ChatCompletionRequest(
            model = ModelId(Config.GTP_MODEL),
            messages = listOf(
                ChatMessage(
                    role = ChatRole.System,
                    content = "Eeres un asistente especializado en resumir texto de manera extremadamente concisa y eficaz."
                ),
                ChatMessage(
                    role = ChatRole.User,
                    content = "Lista las palabras clave del siguiente texto: $info"
                )
            )
        )
        info =
            openAI.chatCompletion(chatCompletionRequest).choices.first().message?.content.toString()

        stopLoading()

    }

    //Generacion
    @OptIn(BetaOpenAI::class)
    fun generateImage(
        context: Context,
        tipo1: String,
        tipo2: String,
        caracter: String,
        tamaño: String,
        masked: Boolean,
        imageURL: (String) -> Unit
    ) =
        viewModelScope.launch {

            startLoading()

//            var prompt = "pokemon image, vector image, ${tipo1.trim()}, ${tipo2.trim()}, ${caracter.trim()}, ${tamaño.trim()}"

            var prompt =
                "Generate an image of a Pokémon, vector image, with the following characteristics: \n" +
                        "- Types: ${tipo1.trim()}, ${tipo2.trim()}\n" +
                        "- Character:  ${caracter.trim()} \n" +
                        "- Size: ${tamaño.trim()}\n"


            if (info.isNotEmpty()) {
//                prompt += ", ${info.trim()}"
                prompt += "-Additional Features: ${info.trim()}"
            }

            val images: List<ImageURL>

            if (masked) {

                images = openAI.imageURL(
                    ImageEdit(
                        image = FileSource(
                            name = Config.IMAGE_FILE,
                            source = context.resources.openRawResource(R.raw.pokeball).source()
                        ),
                        mask = FileSource(
                            name = Config.MASK_FILE,
                            source = context.resources.openRawResource(R.raw.pokeball2).source()
                        ),
                        prompt = prompt,
                        n = 1,
                        size = ImageSize.is1024x1024
                    )
                )
            }else{
                images = openAI.imageURL(
                    creation = ImageCreation(
                        prompt = prompt,
                        n = 1,
                        size = ImageSize.is1024x1024
                    )
                )
            }

            imageURL(images.first().url)

            stopLoading()

        }

    //Transcription

    @OptIn(BetaOpenAI::class)
    private fun loadInfo(file: File?) = viewModelScope.launch {

        file?.source()?.let {
            startLoading()

            val transcriptionRequest = TranscriptionRequest(
                audio = FileSource(name = Config.AUDIO_FILE, source = it),
                model = ModelId(Config.WHISPER_MODEL)
            )
            val transcription = openAI.transcription(transcriptionRequest)
            info = transcription.text

            stopLoading()
        }
    }


    //Loading

    private fun startLoading() {
        loading = true
    }

    private fun stopLoading() {
        loading = false
    }


}
