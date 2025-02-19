package com.leprieto.isntgram.view

import android.util.Patterns
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.leprieto.isntgram.model.api.User
import com.leprieto.isntgram.view.enums.NavigationControllerValues
import com.leprieto.isntgram.viewmodel.states.GenericRequestState

@Composable
fun RegisterScreenComposable(
    registerState: GenericRequestState,
    navigate: (String) -> Unit,
    register: (User) -> Unit,
    modifier: Modifier = Modifier.padding(12.dp)
) {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    val usernameError = username.length < 3
    val emailError = email.isNotEmpty() && !Patterns.EMAIL_ADDRESS.matcher(email).matches()
    val passwordError = confirmPassword.isNotEmpty() && password != confirmPassword
    val enableRegister =
        username.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty() && !usernameError && !emailError && !passwordError
    LaunchedEffect(key1 = registerState) {
        if (registerState is GenericRequestState.Success) {
            navigate(NavigationControllerValues.LOGIN.name)
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
            value = email,
            onValueChange = { email = it },
            label = { Text(text = "Email") },
            isError = emailError
        )
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 4.dp),
            value = password,
            onValueChange = { password = it },
            label = { Text(text = "Password") },
            visualTransformation = PasswordVisualTransformation(),
            isError = passwordError
        )
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 4.dp),
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text(text = "Confirm Password") },
            visualTransformation = PasswordVisualTransformation(),
            isError = passwordError
        )
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 4.dp),
            onClick = {
                register(User(username, password, email))
            },
            enabled = enableRegister
        ) {

            Box(
                modifier = Modifier.height(24.dp), contentAlignment = Alignment.Center
            ) {
                when (registerState) {
                    is GenericRequestState.Error, GenericRequestState.Idle -> Text(text = "Register")
                    GenericRequestState.Loading -> CircularProgressIndicator(
                        modifier = Modifier.size(
                            24.dp
                        ), color = Color.White
                    )

                    is GenericRequestState.Success -> {
                        Text(text = "Success")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RegisterScreenComposablePreview() {
//    RegisterScreenComposable(navigate = {})
}