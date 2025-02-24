package com.leprieto.isntgram.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.leprieto.isntgram.ui.theme.IsntGramTheme
import com.leprieto.isntgram.view.main.MainScreenComposable
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IsntGramTheme {
                MainScreenComposable()
            }
        }
    }
}


