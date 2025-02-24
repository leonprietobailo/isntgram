package com.leprieto.isntgram.view.main

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.leprieto.isntgram.ext.toFile
import com.leprieto.isntgram.ext.toRemote
import com.leprieto.isntgram.model.api.Post
import com.leprieto.isntgram.model.db.UserDetailsLocal
import com.leprieto.isntgram.viewmodel.states.GenericRequestState
import com.leprieto.isntgram.viewmodel.states.UserDetailsState
import java.io.File


@Composable
fun ImageUploadMainComposable(
    loginState: UserDetailsState.Success,
    imagePostedState: GenericRequestState,
    uploadPost: (Post, File) -> Unit
) {
    when (imagePostedState) {
        is GenericRequestState.Error -> ImageUploadErrorComposable()
        GenericRequestState.Idle -> ImageUploadIdleComposable(loginState, uploadPost)
        GenericRequestState.Loading -> ImageUploadLoadingComposable()
        is GenericRequestState.Success -> ImageUploadSuccessComposable()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImageUploadIdleComposable(
    loginState: UserDetailsState.Success,
    uploadPost: (Post, File) -> Unit
) {
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var caption by remember { mutableStateOf("") }
    val current = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri:
        Uri? ->
        imageUri = uri
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp)
    ) {
        TopAppBar(
            title = { Text(text = "New post.") }, windowInsets = WindowInsets(
                top = 0.dp, bottom = 0.dp
            )
        )
        imageUri?.let {
            Box(
                modifier =
                Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            ) {
                AsyncImage(
                    model = imageUri,
                    contentDescription = "Selected Image",
                    modifier = Modifier
                        .fillMaxSize(),
//                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.FillWidth
                )
            }
            TextField(
                value = caption,
                onValueChange = { caption = it },
                label = { Text(text = "Caption") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Button(modifier = Modifier.fillMaxWidth(), onClick = { launcher.launch("image/*") }) {
            Text(text = "Select image...")
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                imageUri?.let {
                    uploadPost(
                        Post(loginState.userDetailsLocal.toRemote(), caption, null),
                        it.toFile(current)
                    )
                }
            },
            enabled = imageUri != null
        ) {
            Text(text = "Post")
        }
    }
}

@Composable
fun ImageUploadLoadingComposable() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Uploading post...")
        Spacer(modifier = Modifier.size(8.dp))
        CircularProgressIndicator()
    }
}

@Composable
fun ImageUploadSuccessComposable() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Post uploaded successfully!")
    }
}

@Composable
fun ImageUploadErrorComposable() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Error while creating the post.")
    }
}

// ******************************
// *          PREVIEW           *
// ******************************

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ImageUploadMainComposablePreview(@PreviewParameter(UploadPictureProvider::class) imagePostedState: GenericRequestState) {
    ImageUploadMainComposable(
        loginState = UserDetailsState.Success(UserDetailsLocal("omega", "")),
        imagePostedState = imagePostedState
    ) { post: Post, file: File ->
    }
}

// PROVIDER OBJECTS.
private class UploadPictureProvider : PreviewParameterProvider<GenericRequestState> {
    override val values: Sequence<GenericRequestState> = sequenceOf(
        GenericRequestState.Idle,
        GenericRequestState.Loading,
        GenericRequestState.Error(""),
        GenericRequestState.Success(null)
    )
}