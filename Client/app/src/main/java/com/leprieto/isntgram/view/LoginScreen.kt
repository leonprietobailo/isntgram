package com.leprieto.isntgram.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.leprieto.isntgram.view.enums.NavigationControllerValues

@Composable
fun LoginScreenComposable(
    navController: NavController, modifier: Modifier = Modifier.padding(12.dp)
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 4.dp),
            value = username,
            onValueChange = { username = it },
            placeholder = { Text(text = "Username") })
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 4.dp),
            value = password,
            onValueChange = { password = it },
            placeholder = { Text(text = "Password") },
            visualTransformation = PasswordVisualTransformation()
        )
        Button(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 4.dp),
            onClick = { navController.navigate(NavigationControllerValues.LOGIN.name) }) {
            Text(text = "Login")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenComposablePreview() {
    LoginScreenComposable(navController = rememberNavController())
}