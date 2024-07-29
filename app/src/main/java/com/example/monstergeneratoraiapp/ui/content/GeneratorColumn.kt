package com.example.monstergeneratoraiapp.ui.content

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CatchingPokemon
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.monstergeneratoraiapp.ui.components.ActionButton
import com.example.monstergeneratoraiapp.ui.components.TitleText
import com.example.monstergeneratoraiapp.viewmodel.MonsterGeneratorViewModel

@Composable
fun GeneratorColumn(
    context: Context,
    viewModel: MonsterGeneratorViewModel,
    games: String,
    elements: String
) {

    var imageURL by remember { mutableStateOf("") }

    var masked by remember { mutableStateOf(false) }

    val clipBoard = LocalClipboardManager.current

    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {

        TitleText("Generar Pokemon")

        ActionButton(
            "Generar",
            Icons.Filled.CatchingPokemon,
            "Genera el Pokemon",
            enabled = games.isNotEmpty() && elements.isNotEmpty()
        ) {

            viewModel.generateImage(context,games, elements, masked) {
                imageURL = it
            }

            Row(
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(text = "Usar Mascara", fontSize = 20.sp, fontWeight = FontWeight.Bold)

                Spacer(modifier = Modifier.size(8.dp))

                Switch(checked = masked , onCheckedChange = { masked= it})
            }


        }

        if (imageURL.isNotEmpty()) {
            AsyncImage(
                model = imageURL,
                contentDescription = "monster",
                modifier = Modifier.fillMaxSize()
            )
        }


        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxSize()
        ) {

            IconButton(onClick = {
                clipBoard.setText(AnnotatedString(imageURL))
                Toast.makeText(context, "URL copiada", Toast.LENGTH_SHORT).show()
            }) {
                Icon(
                    Icons.Filled.ContentCopy,
                    contentDescription = "Copiar URL",
                    modifier = Modifier.size(ButtonDefaults.IconSize)
                )
            }

            Text(text = "Copiar Imagen", fontSize = 20.sp, fontWeight = FontWeight.Bold)

        }

    }
}