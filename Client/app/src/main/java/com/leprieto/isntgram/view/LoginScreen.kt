package com.leprieto.isntgram.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.leprieto.isntgram.dao.remote.RemoteUser
import com.leprieto.isntgram.viewmodel.RemoteViewModel

//private val remoteUserViewModel: RemoteUserViewModel by viewModels()

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun LoginScreenMainComposable(
    modifier: Modifier = Modifier.padding(12.dp),
    remoteViewModel: RemoteViewModel = hiltViewModel()
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TextField(value = "Username", onValueChange = { username = it })
//        Spacer(modifier = modifier.width(2.dp))
        TextField(value = "Password", onValueChange = { password = it })
//        Spacer(modifier = modifier.width(2.dp))
        Button(onClick = {
            remoteViewModel.login(RemoteUser(username, password, ""))
        }) {
            Text(text = "Login")
        }
    }

}