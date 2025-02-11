package com.leprieto.isntgram.util

import com.leprieto.isntgram.view.screen.Screen

fun String.toScreen(): Screen = when (this) {
    Screen.Home.route -> Screen.Home
    Screen.Search.route -> Screen.Search
    Screen.Add.route -> Screen.Add
    Screen.Reels.route -> Screen.Reels
    Screen.Profile.route -> Screen.Profile
    Screen.OtherProfile.route -> Screen.OtherProfile
    Screen.EditProfile.route -> Screen.EditProfile
//    Screen.Home.route -> Screen.Home
//    Screen.Home.route -> Screen.Home
    else -> throw IllegalArgumentException("Route is not defined on Screen sealed class.")
}