package com.example.monstergeneratoraiapp.ui.content

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Compress
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.monstergeneratoraiapp.ui.components.ActionButton
import com.example.monstergeneratoraiapp.ui.components.TitleText
import com.example.monstergeneratoraiapp.viewmodel.MonsterGeneratorViewModel

@Composable
fun InfoColumn(context: Context, viewModel: MonsterGeneratorViewModel) {

    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        TitleText(text = "Añade los datos necesarios")

        ActionButton(
            if (viewModel.recording) "Finalizar Grabación" else "Iniciar Grabación",
            Icons.Filled.Mic,
            "Grabar las características"
        ) {
            viewModel.recordAudio(context)
        }

        ActionButton("Resumir", Icons.Filled.Compress, "Resumir la grabación") {
            viewModel.createInfoSummary()
        }

        if (viewModel.info.isNotEmpty()){
            Text(text = viewModel.info)
        }
    }
}