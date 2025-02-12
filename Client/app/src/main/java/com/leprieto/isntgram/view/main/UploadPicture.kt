package com.leprieto.isntgram.view.main

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true, showSystemUi = true)
fun ImageUploadMainComposable() {
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var caption by remember { mutableStateOf("") }
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
            onClick = { launcher.launch("image/*") },
            enabled = imageUri != null
        ) {
            Text(text = "Post")
        }
    }
}