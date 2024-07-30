package com.example.monstergeneratoraiapp.ui.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.monstergeneratoraiapp.ui.components.DataTextField
import com.example.monstergeneratoraiapp.ui.components.DropDownMenu
import com.example.monstergeneratoraiapp.ui.components.TitleText
import com.example.monstergeneratoraiapp.ui.data.Caracteres
import com.example.monstergeneratoraiapp.ui.data.Tamaños
import com.example.monstergeneratoraiapp.ui.data.Tipos

@Composable
fun DataColumn(
    tipo1: String,
    tipo2: String,
    caracter: String,
    tamaño: String,
    onTipo1Change: (String) -> Unit,
    onTipo2Change: (String) -> Unit,
    onCaracterChange: (String) -> Unit,
    onTamañoChange: (String) -> Unit
) {
    Column (verticalArrangement = Arrangement.spacedBy(16.dp)) {

        TitleText("Añade los datos necesarios")

//        DataTextField("Tipos", tipos, onTiposChange)
        
        DropDownMenu( "Selecciona un tipo", options = Tipos.values().toList(), tipo1 ,onValueChange = onTipo1Change)

        DropDownMenu("Selecciona un tipo", options = Tipos.values().toList(), tipo2 ,onValueChange = onTipo2Change)

        DropDownMenu("Selecciona un cáracter", options = Caracteres.values().toList(), caracter ,onValueChange = onCaracterChange)

        DropDownMenu("Selecciona un tamaño", options = Tamaños.values().toList(), tamaño ,onValueChange = onTamañoChange)


    }
}