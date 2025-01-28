package com.leprieto.isntgram.view

//import androidx.compose.foundation.layout.FlowRowScopeInstance.align

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.leprieto.isntgram.R
import com.leprieto.isntgram.ui.theme.IsntGramTheme
import com.leprieto.isntgram.view.main.MainScreenComposable
import dagger.hilt.android.AndroidEntryPoint
import com.leprieto.isntgram.view.enums.NavigationControllerValues as NVC

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IsntGramTheme {
                val navController = rememberNavController()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
//                    bottomBar = { BottomNavigationBar(navController) }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = NVC.LANDING.screen,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(NVC.LANDING.screen) {
                            LandingScreenComposable(navController = navController)
                        }
                        composable(NVC.REGISTER.screen) {
                            RegisterScreenComposable(navController = navController)
                        }
                        composable(NVC.LOGIN.screen) {
                            LoginScreenComposable(navController = navController)
                        }
                        composable(NVC.MAIN.screen) {
                            MainScreenComposable()
                        }
                        composable(NVC.DUMMY.screen) {
                            DummyScreenComposable()
                        }
//                        composable(NVC.ADD.screen) {
//                            Screen(
//                                NVC.ADD.screen,
//                                remoteUserViewModel = remoteUserViewModel,
//                                navController
//                            )
//                        }
//                        composable(NVC.LOGIN.screen) {
//                            Screen(
//                                NVC.LOGIN.screen,
//                                remoteUserViewModel = remoteUserViewModel,
//                                navController
//                            )
//                        }
//                        composable(NVC.PROFILE.screen) {
//                            Screen(
//                                NVC.PROFILE.screen,
//                                remoteUserViewModel = remoteUserViewModel,
//                                navController
//                            )
//                        }
//                        composable(
//                            NVCBSE.OTHER_PROFILE.screen,
//                            arguments = listOf(navArgument(NVCBSE.OTHER_PRFILE_ARGUMENT.screen) {
//                                type = NavType.StringType
//                            })
//                        ) { navBackStackEntry ->
//                            val profileId =
//                                navBackStackEntry.arguments?.getString(NVCBSE.OTHER_PRFILE_ARGUMENT.screen)
//                            OtherProfileMainComposable(
//                                remoteUserViewModel = remoteUserViewModel,
//                                profileId = profileId!!
//                            )
//                        }
                    }
                }
            }
        }
    }
}

//@Composable
//fun Screen(
//    name: String, remoteUserViewModel: RemoteUserViewModel, navController: NavHostController
//) {
//    when (name) {
//        NVC.HOME.screen -> DummyScreen(name, remoteUserViewModel)
//        NVC.SEARCH.screen -> SearchMainComposable(
//            remoteUserViewModel = remoteUserViewModel, navController = navController
//        )
//
//        NVC.ADD.screen -> DummyScreen(name, remoteUserViewModel)
////        NVC.LOGIN.screen -> LoginScreenMainComposable()
//        NVC.PROFILE.screen -> SelfProfileMainComposable()
//    }
//}

//@Composable
//fun BottomNavigationBar(navController: NavController) {
//    NavigationBar(modifier = Modifier.height(60.dp)) {
//        NavigationBarItem(selected = navController.currentDestination?.route == NVC.HOME.screen,
//            onClick = { navController.navigate(NVC.HOME.screen) },
//            icon = {
//                Icon(
//                    painter = painterResource(R.drawable.home),
//                    contentDescription = "Home",
//                    modifier = Modifier.size(20.dp)
//                )
//            })
//        NavigationBarItem(selected = navController.currentDestination?.route == NVC.SEARCH.screen,
//            onClick = { navController.navigate(NVC.SEARCH.screen) },
//            icon = {
//                Icon(
//                    painter = painterResource(R.drawable.ic_search),
//                    contentDescription = "Search",
//                    modifier = Modifier.size(20.dp)
//                )
//            })
//        NavigationBarItem(selected = navController.currentDestination?.route == NVC.ADD.screen,
//            onClick = { navController.navigate(NVC.ADD.screen) },
//            icon = {
//                Icon(
//                    painter = painterResource(R.drawable.ic_action_name),
//                    contentDescription = "Post",
//                    modifier = Modifier.size(20.dp)
//                )
//            })
//        NavigationBarItem(selected = navController.currentDestination?.route == NVC.LOGIN.screen,
//            onClick = { navController.navigate(NVC.LOGIN.screen) },
//            icon = {
//                Icon(
//                    painter = painterResource(R.drawable.ic_reels),
//                    contentDescription = "Reels",
//                    modifier = Modifier.size(20.dp)
//                )
//            })
//        NavigationBarItem(selected = navController.currentDestination?.route == NVC.PROFILE.screen,
//            onClick = { navController.navigate(NVC.PROFILE.screen) },
//            icon = {
//                Icon(
//                    painter = painterResource(R.drawable.ic_person),
//                    contentDescription = "Profile",
//                    modifier = Modifier.size(20.dp)
//                )
//            })
//    }
//}

//@Composable
//fun DummyScreen(name: String, remoteUserViewModel: RemoteUserViewModel) {
//    val newUserDetails = UserDetails(
//        id = Math.random().toString(),
////        descripti/on = "",
////        followers = 0,
////        following = 0,
////        posts = 0,
////        name = ""
//    )
//    remoteUserViewModel.addUser(newUserDetails)
//    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
//        Text(text = "$name dummy screen")
//    }
//}


