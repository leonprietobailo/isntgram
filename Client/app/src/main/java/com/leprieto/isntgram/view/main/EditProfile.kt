package com.leprieto.isntgram.view.main

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.leprieto.isntgram.model.api.Profile
import com.leprieto.isntgram.viewmodel.states.GenericRequestState
import com.leprieto.isntgram.viewmodel.states.ProfileDtoState

@Composable
fun EditProfileComposable(
    loadedState: ProfileDtoState,
//    loadProfile: () -> Unit,
    updatedState: GenericRequestState,
    updateProfile: (Profile) -> Unit,
    navigateBack: () -> Unit
) {
    when (loadedState) {
        is ProfileDtoState.Error -> ErrorStateComposable()
        ProfileDtoState.Idle, ProfileDtoState.Loading -> LoadingStateComposable()
        is ProfileDtoState.Success -> LoadedStateComposable(
            loadedState = loadedState,
            updatedState = updatedState,
            updateProfile = updateProfile,
            navigateBack = navigateBack
        )
    }
}

@Composable
private fun ErrorStateComposable() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Unexpected error while retrieving user data.")
    }
}

@Composable
private fun LoadingStateComposable() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Loading...")
        CircularProgressIndicator(modifier = Modifier.padding(8.dp))
    }
}

@Composable
private fun LoadedStateComposable(
    loadedState: ProfileDtoState.Success,
    updatedState: GenericRequestState,
    updateProfile: (Profile) -> Unit,
    navigateBack: () -> Unit
) {
    val context = LocalContext.current
    val profileDto = loadedState.response
    var name by remember { mutableStateOf(profileDto.name ?: "") }
    var description by remember {
        mutableStateOf(
            profileDto.description ?: ""
        )
    }
    LaunchedEffect(key1 = updatedState) {
        if (updatedState is GenericRequestState.Success) {
            Toast.makeText(context, "Profile updated successfully.", Toast.LENGTH_SHORT).show()
        }
    }
    Scaffold(topBar = {
        TopBarComposable(
            onSave = {
                updateProfile(profileDto.copy(name = name, description = description))
            },
            navigateBack = navigateBack
        )
    }) { paddingValues: PaddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            TextField(
                value = profileDto.userId,
                onValueChange = { },
                label = { Text(text = "Username") },
                modifier = Modifier.fillMaxWidth(),
                enabled = false
            )

            TextField(
                value = name,
                onValueChange = { name = it },
                label = { Text(text = "Name") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )
            TextField(
                value = description,
                onValueChange = { description = it },
                label = { Text(text = "Description") },
                modifier = Modifier
                    .fillMaxWidth()

            )
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun TopBarComposable(onSave: () -> Unit, navigateBack: () -> Unit) {
    TopAppBar(
        title = { Text("Edit profile.") },
        actions = {
            IconButton(onClick = {
                onSave()
                navigateBack()
            }) {
                Icon(
                    imageVector = Icons.Default.Done, contentDescription = "Done."
                )
            }
        },
        navigationIcon = {
            IconButton(onClick = { navigateBack() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                )
            }
        })
}


// Preview
@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun EditProfileComposablePreview() {
//    EditProfileComposable(loadedState = ProfileDtoState.
////    Error(""),
//    Success(
//        ProfileDto(
//            "omega", "León", "Lorem Ipsum.", 0, 0, 0
//        )
//
//    ),
//        loadProfile = {}, updateProfile = {}) {}
}
