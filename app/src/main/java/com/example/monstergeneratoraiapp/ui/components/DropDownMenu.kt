package com.example.monstergeneratoraiapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.SwipeDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> DropDownMenu(etiqueta:String ,options:List<T>) {

    // Estado para controlar si el menú desplegable está expandido o no.
    var expanded by remember { mutableStateOf(false) }
    var enable by remember { mutableStateOf(false) }


    Column(modifier= Modifier.fillMaxWidth(),verticalArrangement = Arrangement.SpaceBetween) {
        OutlinedTextField(
            value = "",
            enabled = enable,
            onValueChange = {  },
            label = { Text(text = etiqueta, color = Color.White) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Rounded.SwipeDown,
                    contentDescription = "tipo"
                )
            },
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .size(300.dp, 60.dp)
                .clickable {
                    enable = false
                    expanded = true
                },
            singleLine = true,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                disabledTextColor = Color.White,
                disabledBorderColor = MaterialTheme.colorScheme.outline,
                disabledLeadingIconColor = MaterialTheme.colorScheme.outline
            )
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .size(300.dp)
                .fillMaxHeight().background(Color(0xFF99E2B4))
        ) {
            options.forEach { tipo ->
                DropdownMenuItem(onClick = {
                    expanded = false
                }, text = {
                    Text(text = tipo.toString())
                }, colors = MenuDefaults.itemColors(textColor = Color.White))

            }
        }
    }
}