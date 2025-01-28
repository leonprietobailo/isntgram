package com.leprieto.isntgram.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.leprieto.isntgram.view.enums.NavigationControllerValues

@Composable
fun LandingScreenComposable(
    navController: NavController,
    modifier: Modifier = Modifier.padding(12.dp)
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            onClick = { navController.navigate(NavigationControllerValues.LOGIN.screen) }) {
            Text(text = "Login")
        }
        FilledTonalButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            onClick = { navController.navigate(NavigationControllerValues.REGISTER.screen) }) {
            Text(text = "Register")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LandingScreenComposablePreview() {
    LandingScreenComposable(navController = rememberNavController())
}