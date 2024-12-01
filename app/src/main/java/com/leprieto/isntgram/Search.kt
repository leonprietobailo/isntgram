package com.leprieto.isntgram

//import androidx.compose.foundation.layout.FlowRowScopeInstance.align
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
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
import androidx.compose.ui.unit.dp

@Composable
@Preview(showSystemUi = true)
fun SearchMainComposable(modifier: Modifier = Modifier.padding(12.dp)) {
    TopBar(modifier)

}

@Composable
private fun TopBar(modifier: Modifier) {
    val items =
        listOf(
            "omegaisugly",
            "omegaisnkd",
            "sample",
            "test",
            "dummy",
            "mcubix",
            "ficherobsky",
            "lola",
            "loli",
            "lolipop"
        )
    var searchedValue by remember { mutableStateOf("") }
    var filteredItems by remember { mutableStateOf(items) }

    Column {
        Row() {
            TextField(
                modifier = modifier.fillMaxWidth(),
                value = searchedValue,
                onValueChange = { newValue ->
                    searchedValue = newValue
                    filteredItems =
                        items.filter {
                            it.startsWith(searchedValue)
                        }
                },
                placeholder = {
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
        LazyColumn(modifier = modifier.fillMaxWidth()) {
            items(
                filteredItems,
            ) {
                ResultEntry(it)
            }
        }
    }
}

@Composable
private fun ResultEntry(id: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 6.dp),
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
                text = id
            )
            Text(modifier = Modifier.padding(horizontal = 16.dp), text = id)
        }
    }
}


