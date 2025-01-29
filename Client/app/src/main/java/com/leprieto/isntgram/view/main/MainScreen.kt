package com.leprieto.isntgram.view.main

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.leprieto.isntgram.R
import com.leprieto.isntgram.view.DummyScreenComposable
import com.leprieto.isntgram.view.enums.NavigationControllerValues

@Composable
@Preview(showSystemUi = true, showBackground = true)
fun MainScreenComposable() {
    val mainNavController = rememberNavController()
    Scaffold(modifier = Modifier.fillMaxSize(),
        bottomBar = { BottomNavigationBarComposable(navController = mainNavController) }) { paddingValues: PaddingValues ->
        NavHost(
            navController = mainNavController,
            startDestination = NavigationControllerValues.PROFILE.screen,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(NavigationControllerValues.HOME.screen) {
                DummyScreenComposable()
            }
            composable(NavigationControllerValues.SEARCH.screen) {
                DummyScreenComposable()
            }
            composable(NavigationControllerValues.ADD.screen) {
                DummyScreenComposable()
            }
            composable(NavigationControllerValues.REELS.screen) {
                DummyScreenComposable()
            }
            composable(NavigationControllerValues.PROFILE.screen) {
                SelfProfileMainComposable()
            }
        }
    }
}


@Composable
fun BottomNavigationBarComposable(navController: NavController) {
    NavigationBar(modifier = Modifier.height(60.dp)) {
        NavigationBarItem(selected = navController.currentDestination?.route == NavigationControllerValues.HOME.screen,
            onClick = { navController.navigate(NavigationControllerValues.HOME.screen) },
            icon = {
                Icon(
                    painter = painterResource(R.drawable.home),
                    contentDescription = "Home",
                    modifier = Modifier.size(20.dp)
                )
            })
        NavigationBarItem(selected = navController.currentDestination?.route == NavigationControllerValues.SEARCH.screen,
            onClick = { navController.navigate(NavigationControllerValues.SEARCH.screen) },
            icon = {
                Icon(
                    painter = painterResource(R.drawable.ic_search),
                    contentDescription = "Search",
                    modifier = Modifier.size(20.dp)
                )
            })
        NavigationBarItem(selected = navController.currentDestination?.route == NavigationControllerValues.ADD.screen,
            onClick = { navController.navigate(NavigationControllerValues.ADD.screen) },
            icon = {
                Icon(
                    painter = painterResource(R.drawable.ic_action_name),
                    contentDescription = "Post",
                    modifier = Modifier.size(20.dp)
                )
            })
        NavigationBarItem(selected = navController.currentDestination?.route == NavigationControllerValues.LOGIN.screen,
            onClick = { navController.navigate(NavigationControllerValues.LOGIN.screen) },
            icon = {
                Icon(
                    painter = painterResource(R.drawable.ic_reels),
                    contentDescription = "Reels",
                    modifier = Modifier.size(20.dp)
                )
            })
        NavigationBarItem(selected = navController.currentDestination?.route == NavigationControllerValues.PROFILE.screen,
            onClick = { navController.navigate(NavigationControllerValues.PROFILE.screen) },
            icon = {
                Icon(
                    painter = painterResource(R.drawable.ic_person),
                    contentDescription = "Profile",
                    modifier = Modifier.size(20.dp)
                )
            })
    }
}