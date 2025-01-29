package com.leprieto.isntgram.view

//import androidx.compose.foundation.layout.FlowRowScopeInstance.align
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.leprieto.isntgram.R
import com.leprieto.isntgram.model.api.UserDetailsRemote

//import com.leprieto.isntgram.viewmodel.RemoteUserViewModel

//@Composable
//fun SearchMainComposable(
//    modifier: Modifier = Modifier.padding(12.dp),
//    remoteUserViewModel: RemoteUserViewModel,
//    navController: NavHostController
//) {
//    TopBar(modifier, remoteUserViewModel, navController)
//}

//@Composable
//private fun TopBar(
//    modifier: Modifier,
//    remoteUserViewModel: RemoteUserViewModel,
//    navController: NavHostController
//) {
//    var searchedValue by remember { mutableStateOf("") }
//    var filteredItems by remember { mutableStateOf(remoteUserViewModel.users) }
//
//    Column {
//        Row() {
//            TextField(
//                modifier = modifier.fillMaxWidth(),
//                value = searchedValue,
//                onValueChange = { newValue ->
//                    searchedValue = newValue
//                    filteredItems = remoteUserViewModel.getFilteredUsers(startsWith = searchedValue)
//
//                },
//                placeholder = {
//                    Text("Search")
//                },
//                trailingIcon = {
//                    Icon(
//                        imageVector = Icons.Default.Search,
//                        contentDescription = "Search",
//                        modifier = Modifier.size(32.dp)
//                    )
//                })
//        }
//        LazyColumn(modifier = modifier.fillMaxWidth()) {
//            items(
//                filteredItems.value,
//            ) {
//                ResultEntry(it, navController)
//            }
//        }
//    }
//}

@Composable
private fun ResultEntry(userDetailsRemote: UserDetailsRemote, navController: NavHostController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 6.dp)
            .clickable { navController.navigate("profile/${userDetailsRemote.id}") },
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
                text = userDetailsRemote.id
            )
//            Text(modifier = Modifier.padding(horizontal = 16.dp), text = userDetails.name)
        }
    }
}


