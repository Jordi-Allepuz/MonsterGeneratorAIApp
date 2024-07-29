package com.example.monstergeneratoraiapp.ui.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.monstergeneratoraiapp.ui.components.DataTextField
import com.example.monstergeneratoraiapp.ui.components.TitleText

@Composable
fun DataColumn(
    tipos: String,
    tamaño: String,
    onTiposChange: (String) -> Unit,
    onTamañoChange: (String) -> Unit
) {
    Column (verticalArrangement = Arrangement.spacedBy(16.dp)) {

        TitleText("Añade los datos necesarios")

        DataTextField("Tipos", tipos, onTiposChange)

        DataTextField("Tamaño", tamaño, onTamañoChange)
    }
}