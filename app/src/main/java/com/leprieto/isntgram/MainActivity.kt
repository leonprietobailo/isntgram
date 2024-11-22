package com.leprieto.isntgram

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.leprieto.isntgram.ui.theme.IsntGramTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IsntGramTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    IsntGramTheme {
        Greeting("Android")
    }
}

@Preview(showSystemUi = true, showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainBox() {
    TopAppBar(
        title = {  }, modifier = Modifier.padding(12.dp),
        actions = {
//            Box(modifier = Modifier.size(25.dp).background(Color.Blue))
            Icon(
                painter = painterResource(R.drawable.ic_lock),
                contentDescription = "Add image",
                modifier = Modifier.size(40.dp),
                tint = Color.Black
            )
            Text("omegaisugly")
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                painter = painterResource(R.drawable.ic_threads),
                contentDescription = "Add image",
                modifier = Modifier.size(40.dp),
                tint = Color.Black
            )

            Spacer(modifier = Modifier.width(16.dp))

            Icon(
                painter = painterResource(R.drawable.ic_action_name),
                contentDescription = "Add image",
                modifier = Modifier.size(40.dp),
                tint = Color.Black
            )

            Spacer(modifier = Modifier.width(16.dp))
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "Default Burger Menu",
                modifier = Modifier.size(40.dp)
            )

        }
    )


}

