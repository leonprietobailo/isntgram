package com.leprieto.isntgram.view.main

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells.Fixed
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.leprieto.isntgram.R
import com.leprieto.isntgram.view.screen.Screen
import com.leprieto.isntgram.viewmodel.states.PostsState
import com.leprieto.isntgram.viewmodel.states.ProfileDtoState

@Composable
fun SelfProfileMainComposable(
//    modifier: Modifier = Modifier.padding(12.dp),
    loadedState: ProfileDtoState,
    postsState: PostsState,
    loadProfile: () -> Unit,
    editProfile: (String) -> Unit,
) {
    Column {
        when (loadedState) {
            is ProfileDtoState.Error -> {
                ErrorStateComposable(loadProfile)
            }

            ProfileDtoState.Idle, ProfileDtoState.Loading -> {
                LoadingStateComposable()
            }

            is ProfileDtoState.Success -> {
                LoadedStateComposable(
                    loadedState = loadedState,
                    postsState = postsState as PostsState.Success, // Review
                    editProfile = editProfile
                )
            }
        }
    }
}

@Composable
private fun LoadingStateComposable() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Loading user...")
        CircularProgressIndicator(modifier = Modifier.padding(8.dp))
    }
}

@Composable
private fun ErrorStateComposable(loadProfile: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Error while obtaining user profile.")
        Spacer(modifier = Modifier.size(4.dp))
        Button(onClick = { loadProfile() }) {
            Text(text = "Reload")
        }
    }
}

@Composable
private fun LoadedStateComposable(
    loadedState: ProfileDtoState.Success,
    postsState: PostsState.Success,
    editProfile: (String) -> Unit
) {
    ProfileTopBar(loadedState)
    ProfileStatsComposable(loadedState)
    NameAndDescriptionComposable(loadedState)
    FollowMessageButtons(editProfile = editProfile)
    TabSelectorComposable(postsState = postsState)
}

@Composable
private fun ProfileTopBar(loadedState: ProfileDtoState.Success) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(horizontal = 12.dp)
//            .padding(top = 12.dp)
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_lock),
            contentDescription = "Add image",
            modifier = Modifier.size(16.dp),
            tint = Color.Black
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = loadedState.response.userId, fontWeight = FontWeight.Bold, fontSize = 24.sp)
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            painter = painterResource(R.drawable.ic_threads),
            contentDescription = "Add image",
            modifier = Modifier.size(32.dp),
            tint = Color.Black
        )

        Spacer(modifier = Modifier.width(16.dp))

        Icon(
            painter = painterResource(R.drawable.ic_action_name),
            contentDescription = "Add image",
            modifier = Modifier
                .size(32.dp)
                .alpha(100f),
            tint = Color.Black
        )
        Spacer(modifier = Modifier.width(16.dp))
        Icon(
            imageVector = Icons.Default.Menu,
            contentDescription = "Default Burger Menu",
            modifier = Modifier.size(32.dp)
        )
    }
}

@Composable
private fun ProfileStatsComposable(loadedState: ProfileDtoState.Success) {
    Row(
        verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(12.dp)
    ) {
        Image(
            modifier = Modifier
                .size(72.dp)
                .clip(CircleShape)
                .border(BorderStroke(2.dp, Color.Gray), CircleShape)
                .padding(),
            painter = painterResource(id = R.drawable.ic_person),
            contentDescription = "Profile Picture"

        )
        Row(
            modifier = Modifier
                .weight(3f)
                .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ProfileNumberIndicatorComposable(loadedState.response.posts, "posts")
            ProfileNumberIndicatorComposable(loadedState.response.followers, "followers")
            ProfileNumberIndicatorComposable(loadedState.response.following, "following")
        }
    }
}

@Composable
private fun NameAndDescriptionComposable(loadedState: ProfileDtoState.Success) {
    loadedState.response.name?.let {
        Text(
            it, fontSize = 12.sp, modifier = Modifier.padding(
                horizontal = 12.dp
            ), fontWeight = FontWeight.Bold
        )
    }
    loadedState.response.description?.let {
        Text(
            text = it,
            fontSize = 12.sp,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
        )
    }
}

@Composable
private fun FollowMessageButtons(editProfile: (String) -> Unit) {
    Row(modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)) {
        FilledTonalButton(modifier = Modifier.weight(4f),
            shape = RoundedCornerShape(12.dp),
            onClick = { editProfile(Screen.EditProfile.route) }) {
            Text("Edit profile")
        }
        Spacer(modifier = Modifier.size(8.dp))
        FilledTonalButton(modifier = Modifier.weight(4f),
            shape = RoundedCornerShape(12.dp),
            onClick = {}) {
            Text("Share profile")
        }
        Spacer(modifier = Modifier.size(8.dp))
        FilledTonalButton(modifier = Modifier.weight(1f),
            shape = RoundedCornerShape(12.dp),
            contentPadding = PaddingValues(0.dp),
            onClick = {}) {
            Icon(
                painter = painterResource(R.drawable.ic_add_person),
                contentDescription = "Add image",
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

@Composable
private fun TabSelectorComposable(
    postsState: PostsState.Success
) {
    var selectedTab by remember { mutableIntStateOf(0) }
    Column(modifier = Modifier.padding(horizontal = 12.dp)) {
        TabRow(
            selectedTabIndex = selectedTab,
        ) {
            Tab(selected = selectedTab == 0, onClick = { selectedTab = 0 }, content = {
                Box(modifier = Modifier.padding(16.dp)) {
                    Icon(
                        painter = painterResource(R.drawable.ic_dashboard),
                        contentDescription = "",
                        modifier = Modifier.size(20.dp)
                    )
                }
            })
            Tab(selected = selectedTab == 1, onClick = { selectedTab = 1 }, content = {
                Box(modifier = Modifier.padding(16.dp)) {
                    Icon(
                        painter = painterResource(R.drawable.ic_reels),
                        contentDescription = "",
                        modifier = Modifier.size(20.dp)
                    )
                }
            })
            Tab(selected = selectedTab == 2, onClick = { selectedTab = 2 }, content = {
                Box(modifier = Modifier.padding(16.dp)) {
                    Icon(
                        painter = painterResource(R.drawable.ic_tagged),
                        contentDescription = "",
                        modifier = Modifier.size(20.dp)
                    )
                }
            })
        }
        when (selectedTab) {
            0 -> TabDashboardComposable(postsState = postsState)
            1 -> TabReelsComposable()
            2 -> TabTaggedComposable()
        }
    }
}

@Composable
private fun TabDashboardComposable(postsState: PostsState.Success) {
    LazyVerticalGrid(
        columns = Fixed(3),
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp),
    ) {
        items(postsState.response.size) {
            Box(modifier = Modifier.aspectRatio(1f)) {
                val url = "http://192.168.1.150:8080/images/" + postsState.response[it].url
                AsyncImage(
                    model = url,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}

@Composable
private fun TabReelsComposable() {
    Box {
        Text("TODO")
    }
}

@Composable
private fun TabTaggedComposable() {
    Box {
        Text("TODO")
    }
}

@Composable
private fun ProfileNumberIndicatorComposable(number: Int, text: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(number.toString(), fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Text(text)
    }
}

// ******************************
// *          PREVIEW           *
// ******************************

//@Composable
//@Preview(showBackground = true, showSystemUi = true)
//private fun SelfProfileMainComposablePreview() {
//    SelfProfileMainComposable(loadedState =
////        ProfileDtoState.Error("Message")
//    ProfileDtoState.Success(
//        ProfileDto(
//            "omega", "sample", "sample sample", 0, 0, 0
//        )
//    ), loadProfile = {}, editProfile = { }, postsState = postViewModel.loadedPosts
//    )
//}

