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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.leprieto.isntgram.R
import com.leprieto.isntgram.ext.toScreen
import com.leprieto.isntgram.view.DummyScreenComposable
import com.leprieto.isntgram.view.screen.Screen
import com.leprieto.isntgram.viewmodel.LoggedAccountViewModel
import com.leprieto.isntgram.viewmodel.PostViewModel
import com.leprieto.isntgram.viewmodel.ProfileViewModel
import com.leprieto.isntgram.viewmodel.states.UserDetailsState


@Composable
fun MainScreenComposable(loginState: UserDetailsState.Success) {
    val mainNavController = rememberNavController()
    val navBackStackEntry = mainNavController.currentBackStackEntry
    val currentRoute = navBackStackEntry?.destination?.route
    val currentScreen = currentRoute?.toScreen()

    Scaffold(modifier = Modifier.fillMaxSize(),
        bottomBar = {
//            if (currentScreen?.showBottomBar == true) {
            BottomNavigationBarComposable(navController = mainNavController)
//            }
        }) { paddingValues: PaddingValues ->
        NavHost(
            navController = mainNavController,
            startDestination = Screen.Profile.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Screen.Home.route) {
                DummyScreenComposable()
            }
            composable(Screen.Search.route) {
                val profileViewModel: ProfileViewModel = hiltViewModel()
                SearchMainComposable(
                    searchRequestState = profileViewModel.searchProfilesState,
                    searchProfile = profileViewModel::searchProfiles,
                    navToProfile = mainNavController::navigate
                )
            }
            composable(Screen.Add.route) {
                val postViewModel: PostViewModel = hiltViewModel()
                ImageUploadMainComposable(
                    loginState,
                    postViewModel.imagePostedState,
                    postViewModel::uploadImage
                )
            }
            composable(Screen.Reels.route) {
                DummyScreenComposable()
            }
            composable(Screen.Profile.route) {
                val loggedAccountViewModel: LoggedAccountViewModel = hiltViewModel()
                val postViewModel: PostViewModel = hiltViewModel()
                LaunchedEffect(key1 = Unit) {
                    loggedAccountViewModel.loadProfile()
                    postViewModel.getPosts(loginState.userDetailsLocal.id)
                }
                SelfProfileMainComposable(
                    loadedState = loggedAccountViewModel.selfLoadState,
                    postsState = postViewModel.loadedPostsState,
                    loadProfile = loggedAccountViewModel::loadProfile,
                    editProfile = mainNavController::navigate,

                    )
            }
            composable(Screen.OtherProfile.route) { navBackStackEntry ->
                val profileId = navBackStackEntry.arguments?.getString("profileId")
                val profileViewModel: ProfileViewModel = hiltViewModel()
                profileId?.let {
                    LaunchedEffect(key1 = Unit) {
                        profileViewModel.loadProfile(it)
                    }
                    OtherProfileMainComposable(
                        loadedState = profileViewModel.loadProfileState,
                        loadProfile = { (profileViewModel::loadProfile)(it) }
                    )
                }

            }
            composable(Screen.EditProfile.route) {
                val loggedAccountViewModel: LoggedAccountViewModel = hiltViewModel()
                LaunchedEffect(key1 = Unit) {
                    loggedAccountViewModel.loadProfile()
                }
                EditProfileComposable(
                    loadedState = loggedAccountViewModel.selfLoadState,
                    updatedState = loggedAccountViewModel.selfUpdateState,
//                    loadProfile = loggedAccountViewModel::loadProfile,
                    updateProfile = loggedAccountViewModel::updateProfile,
                    navigateBack = mainNavController::popBackStack
                )
            }
        }
    }
}


@Composable
fun BottomNavigationBarComposable(navController: NavController) {
    NavigationBar(modifier = Modifier.height(60.dp)) {
        NavigationBarItem(selected = navController.currentDestination?.route == Screen.Home.route,
            onClick = { navController.navigate(Screen.Home.route) },
            icon = {
                Icon(
                    painter = painterResource(R.drawable.home),
                    contentDescription = "Home",
                    modifier = Modifier.size(20.dp)
                )
            })
        NavigationBarItem(selected = navController.currentDestination?.route == Screen.Search.route,
            onClick = { navController.navigate(Screen.Search.route) },
            icon = {
                Icon(
                    painter = painterResource(R.drawable.ic_search),
                    contentDescription = "Search",
                    modifier = Modifier.size(20.dp)
                )
            })
        NavigationBarItem(selected = navController.currentDestination?.route == Screen.Add.route,
            onClick = { navController.navigate(Screen.Add.route) },
            icon = {
                Icon(
                    painter = painterResource(R.drawable.ic_action_name),
                    contentDescription = "Post",
                    modifier = Modifier.size(20.dp)
                )
            })
        NavigationBarItem(selected = navController.currentDestination?.route == Screen.Reels.route,
            onClick = { navController.navigate(Screen.Reels.route) },
            icon = {
                Icon(
                    painter = painterResource(R.drawable.ic_reels),
                    contentDescription = "Reels",
                    modifier = Modifier.size(20.dp)
                )
            })
        NavigationBarItem(selected = navController.currentDestination?.route == Screen.Profile.route,
            onClick = { navController.navigate(Screen.Profile.route) },
            icon = {
                Icon(
                    painter = painterResource(R.drawable.ic_person),
                    contentDescription = "Profile",
                    modifier = Modifier.size(20.dp)
                )
            })
    }
}