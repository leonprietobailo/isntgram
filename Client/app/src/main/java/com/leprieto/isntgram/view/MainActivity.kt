package com.leprieto.isntgram.view

//import androidx.compose.foundation.layout.FlowRowScopeInstance.align

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.leprieto.isntgram.R
import com.leprieto.isntgram.model.RemoteUser
import com.leprieto.isntgram.ui.theme.IsntGramTheme
import com.leprieto.isntgram.viewmodel.RemoteUserViewModel
import dagger.hilt.android.AndroidEntryPoint
import com.leprieto.isntgram.view.enums.NavigationControllerBse as NVCBSE
import com.leprieto.isntgram.view.enums.NavigationControllerValues as NVC

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val remoteUserViewModel: RemoteUserViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IsntGramTheme {
                val navController = rememberNavController()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = { BottomNavigationBar(navController) }
                )
                { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = NVC.HOME.screen,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(NVC.HOME.screen) {
                            Screen(
                                NVC.HOME.screen,
                                remoteUserViewModel = remoteUserViewModel,
                                navController = navController
                            )
                        }
                        composable(NVC.SEARCH.screen) {
                            Screen(
                                NVC.SEARCH.screen,
                                remoteUserViewModel = remoteUserViewModel,
                                navController
                            )
                        }
                        composable(NVC.ADD.screen) {
                            Screen(
                                NVC.ADD.screen,
                                remoteUserViewModel = remoteUserViewModel,
                                navController
                            )
                        }
                        composable(NVC.REELS.screen) {
                            Screen(
                                NVC.REELS.screen,
                                remoteUserViewModel = remoteUserViewModel,
                                navController
                            )
                        }
                        composable(NVC.PROFILE.screen) {
                            Screen(
                                NVC.PROFILE.screen,
                                remoteUserViewModel = remoteUserViewModel,
                                navController
                            )
                        }
                        composable(
                            NVCBSE.OTHER_PROFILE.screen,
                            arguments = listOf(navArgument(NVCBSE.OTHER_PRFILE_ARGUMENT.screen) {
                                type = NavType.StringType
                            })
                        ) { navBackStackEntry ->
                            val profileId =
                                navBackStackEntry.arguments?.getString(NVCBSE.OTHER_PRFILE_ARGUMENT.screen)
                            OtherProfileMainComposable(
                                remoteUserViewModel = remoteUserViewModel,
                                profileId = profileId!!
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Screen(
    name: String,
    remoteUserViewModel: RemoteUserViewModel,
    navController: NavHostController
) {
    when (name) {
        NVC.HOME.screen -> DummyScreen(name, remoteUserViewModel)
        NVC.SEARCH.screen -> SearchMainComposable(
            remoteUserViewModel = remoteUserViewModel,
            navController = navController
        )

        NVC.ADD.screen -> DummyScreen(name, remoteUserViewModel)
        NVC.REELS.screen -> DummyScreen(name, remoteUserViewModel)
        NVC.PROFILE.screen -> SelfProfileMainComposable()
    }
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    NavigationBar(modifier = Modifier.height(60.dp)) {
        NavigationBarItem(
            selected = navController.currentDestination?.route == NVC.HOME.screen,
            onClick = { navController.navigate(NVC.HOME.screen) },
            icon = {
                Icon(
                    painter = painterResource(R.drawable.home),
                    contentDescription = "Home",
                    modifier = Modifier.size(20.dp)
                )
            }
        )
        NavigationBarItem(
            selected = navController.currentDestination?.route == NVC.SEARCH.screen,
            onClick = { navController.navigate(NVC.SEARCH.screen) },
            icon = {
                Icon(
                    painter = painterResource(R.drawable.ic_search),
                    contentDescription = "Search",
                    modifier = Modifier.size(20.dp)
                )
            }
        )
        NavigationBarItem(
            selected = navController.currentDestination?.route == NVC.ADD.screen,
            onClick = { navController.navigate(NVC.ADD.screen) },
            icon = {
                Icon(
                    painter = painterResource(R.drawable.ic_action_name),
                    contentDescription = "Post",
                    modifier = Modifier.size(20.dp)
                )
            }
        )
        NavigationBarItem(
            selected = navController.currentDestination?.route == NVC.REELS.screen,
            onClick = { navController.navigate(NVC.REELS.screen) },
            icon = {
                Icon(
                    painter = painterResource(R.drawable.ic_reels),
                    contentDescription = "Reels",
                    modifier = Modifier.size(20.dp)
                )
            }
        )
        NavigationBarItem(
            selected = navController.currentDestination?.route == NVC.PROFILE.screen,
            onClick = { navController.navigate(NVC.PROFILE.screen) },
            icon = {
                Icon(
                    painter = painterResource(R.drawable.ic_person),
                    contentDescription = "Profile",
                    modifier = Modifier.size(20.dp)
                )
            }
        )
    }
}

@Composable
fun DummyScreen(name: String, remoteUserViewModel: RemoteUserViewModel) {
    val newRemoteUser = RemoteUser(
        id = Math.random().toString(),
        description = "",
        followers = 0,
        following = 0,
        posts = 0,
        name = ""
    )
    remoteUserViewModel.addUser(newRemoteUser)
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "$name dummy screen")
    }
}


