package com.leprieto.isntgram

//import androidx.compose.foundation.layout.FlowRowScopeInstance.align
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.leprieto.isntgram.ui.theme.IsntGramTheme

//class Search : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContent {
//            IsntGramTheme {
//                Scaffold(modifier = Modifier.fillMaxSize(),
//                    topBar = { TopBar() },
//                    bottomBar = { BottomNavigationBar() }) { innerPadding ->
//                    MainComposable(modifier = Modifier.padding(innerPadding))
//                }
//            }
//        }
//    }
//}

@Composable
@Preview
fun TopBar() {
    Row {
        TextField(value = "", onValueChange = {}, placeholder = {
            Text("Search")
        }, trailingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
                modifier = Modifier.size(32.dp)
            )
        })
    }
}