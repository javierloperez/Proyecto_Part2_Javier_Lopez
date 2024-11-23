package com.example.proyecto_part1_javier_lopez

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import ui.themes.Proyecto_Part1_Javier_LopezTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Proyecto_Part1_Javier_LopezTheme {
                AplicacionPrincipal()
            }
        }
    }
}