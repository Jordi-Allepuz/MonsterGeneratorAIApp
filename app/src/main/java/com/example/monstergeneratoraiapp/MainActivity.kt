package com.example.monstergeneratoraiapp

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import com.example.monstergeneratoraiapp.ui.content.DataColumn
import com.example.monstergeneratoraiapp.ui.content.GeneratorColumn
import com.example.monstergeneratoraiapp.ui.content.InfoColumn
import com.example.monstergeneratoraiapp.ui.theme.MonsterGeneratorAIAppTheme
import com.example.monstergeneratoraiapp.viewmodel.MonsterGeneratorViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.RECORD_AUDIO), 0)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MonsterGeneratorAIAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Content()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Content() {

    val viewModel = MonsterGeneratorViewModel()

    val context = LocalContext.current

    var tipo1 by remember { mutableStateOf("") }
    var tipo2 by remember { mutableStateOf("") }
    var caracter by remember { mutableStateOf("") }
    var tamaño by remember { mutableStateOf("") }

    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Pokemon Generator") }
            )
        }
    ) { padding ->


        if (viewModel.loading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }


        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .alpha(if (viewModel.loading) 0.5f else 1f)
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {

            DataColumn(
                tipo1,
                tipo2,
                caracter,
                tamaño,
                onTipo1Change = { tipo1 = it },
                onTipo2Change = { tipo2 = it },
                onCaracterChange = { caracter = it },
                onTamañoChange = { tamaño = it }
            )

            InfoColumn(context, MonsterGeneratorViewModel())

            GeneratorColumn(context, viewModel, tipo1, tipo2, caracter, tamaño)

        }

    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MonsterGeneratorAIAppTheme {
        Content()
    }
}