package com.leprieto.isntgram.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.leprieto.isntgram.R
import com.leprieto.isntgram.model.api.ProfileDto
import com.leprieto.isntgram.viewmodel.states.SearchRequestState

@Composable
fun SearchMainComposable(
    searchRequestState: SearchRequestState,
    navToProfile: (String) -> Unit,
    searchProfile: (String) -> Unit
) {
    Column {
        SearchBarComposable(searchProfile = searchProfile)
        ProfileEntriesComposable(
            searchRequestState = searchRequestState, navToProfile = navToProfile
        )
    }
}


//@Preview
@Composable
private fun SearchBarComposable(
    searchProfile: (String) -> Unit
) {
    var searchedValue by remember { mutableStateOf("") }
    Column {
        Row {
            TextField(value = searchedValue,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { newValue ->
                    searchedValue = newValue // Evaluate if storing the value is required.
                    searchProfile(searchedValue)
                },
                label = {
                    Text("Search")
                },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        modifier = Modifier.size(32.dp)
                    )
                })
        }
    }
}

@Composable
private fun ProfileEntriesComposable(
    @PreviewParameter(SearchBarProvider::class) searchRequestState: SearchRequestState,
    navToProfile: (String) -> Unit
) {
    when (searchRequestState) {
        SearchRequestState.Error -> ProfileEntriesErrorStateComposable()
        SearchRequestState.Idle, SearchRequestState.Loading -> ProfileEntriesLoadingStateComposable()

        is SearchRequestState.Success -> ProfileEntriesSuccessStateComposable(searchRequestState)
    }
}

@Composable
private fun ProfileEntriesErrorStateComposable() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Error while retrieving user profiles.")
    }
}

@Composable
private fun ProfileEntriesLoadingStateComposable() {
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
private fun ProfileEntriesSuccessStateComposable(searchRequestState: SearchRequestState.Success) {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(count = searchRequestState.profiles.size,
            key = { index -> searchRequestState.profiles[index].userId }) { index ->
            val profile = searchRequestState.profiles[index]
            ProfileEntryComposable(profile) {}
        }
    }
}

@Composable
private fun ProfileEntryComposable(profileDto: ProfileDto, navToProfile: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .border(BorderStroke(2.dp, Color.Gray), CircleShape),
            painter = painterResource(id = R.drawable.ic_person),
            contentDescription = "Profile Picture"
        )
        Column {
            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                fontWeight = FontWeight.Bold,
                text = profileDto.userId
            )
            profileDto.name?.let {
                Text(
                    modifier = Modifier.padding(horizontal = 16.dp), text = it
                )
            }
        }
    }
}

// PROVIDER OBJECTS.
class SearchBarProvider : PreviewParameterProvider<SearchRequestState> {
    override val values: Sequence<SearchRequestState> = sequenceOf(
        SearchRequestState.Idle, SearchRequestState.Error, SearchRequestState.Success(
            listOf(
                ProfileDto("omega", "León", "", 0, 0, 0),
                ProfileDto("alpha", "Foo Bar", "", 0, 0, 0),
                ProfileDto("beta", null, "", 0, 0, 0),
            )
        )
    )
}


// PREVIEW COMPOSABLES.
//@Preview
@Composable
private fun SearchBarComposablePreview() {
    SearchBarComposable {

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun SearchMainComposablePreview(@PreviewParameter(SearchBarProvider::class) searchRequestState: SearchRequestState) {
    SearchMainComposable(searchRequestState = searchRequestState,
        navToProfile = {},
        searchProfile = {})
}

