package com.leprieto.isntgram.view.screen

sealed class Screen(val route: String, val showBottomBar: Boolean) {
    // Auth


    // Main
    data object Home : Screen("home", true)
    data object Search : Screen("search", true)
    data object Add : Screen("add", true)
    data object Reels : Screen("reels", true)
    data object Profile : Screen("profile", true)
    data object OtherProfile : Screen("profile/{profileId}", true)

    // Opt
    data object EditProfile : Screen("edit_profile", false)
}