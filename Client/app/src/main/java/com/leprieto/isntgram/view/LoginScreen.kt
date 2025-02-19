package com.leprieto.isntgram.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.leprieto.isntgram.model.api.User
import com.leprieto.isntgram.view.enums.NavigationControllerValues
import com.leprieto.isntgram.viewmodel.states.UserDetailsState


@Composable
fun LoginScreenComposable(
    loginState: UserDetailsState,
    navigate: (String) -> Unit,
    login: (User) -> Unit,
    modifier: Modifier = Modifier.padding(12.dp)
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    LaunchedEffect(key1 = loginState) {
        if (loginState is UserDetailsState.Success) {
            navigate(NavigationControllerValues.MAIN.screen)
        }
    }
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
            label = { Text(text = "Username") })
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 4.dp),
            value = password,
            onValueChange = { password = it },
            label = { Text(text = "Password") },
            visualTransformation = PasswordVisualTransformation()
        )
        Button(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 4.dp),
            onClick = {
                login(
                    User(
                        id = username, password = password, email = null
                    )
                )
            }) {
            Box(
                modifier = Modifier.height(24.dp), contentAlignment = Alignment.Center
            ) {
                when (loginState) {
                    is UserDetailsState.Error, UserDetailsState.Idle -> Text(text = "Login")
                    UserDetailsState.Loading -> CircularProgressIndicator(
                        modifier = Modifier.size(
                            24.dp
                        ), color = Color.White
                    )

                    is UserDetailsState.Success -> {
                        Text(text = "Success")
                    }
                }
            }
        }
    }
}

//@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenComposablePreview() {
//    LoginScreenComposable(navigate = {})
}