package com.leprieto.isntgram.ext

import com.leprieto.isntgram.view.screen.Screen

fun String.toScreen(): Screen {
    val sealedSubclasses = Screen::class.sealedSubclasses
    return sealedSubclasses.filter { it.objectInstance != null }
        .map { it.objectInstance!! }.find { it.route == this }
        ?: throw IllegalArgumentException("Route is not found in Screen class.")
}