package com.example.monstergeneratoraiapp.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.sp

@Composable
fun TitleText(text:String) {
    Text(text.uppercase(), fontSize = 16.sp, fontWeight = Bold)
}