package com.example.monstergeneratoraiapp.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.Modifier

@Composable
fun ActionButton(text: String,
                 icon: ImageVector,
                 description: String,
                 enabled: Boolean = true,
                 onClick: () -> Unit) {

    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        enabled = enabled
    ) {
        Text(text)

        Spacer(Modifier.size(ButtonDefaults.IconSpacing))

        Icon(
            imageVector = icon,
            contentDescription = description,
            modifier = Modifier.size(ButtonDefaults.IconSize)
        )
    }

}