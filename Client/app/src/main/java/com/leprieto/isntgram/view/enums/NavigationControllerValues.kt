package com.leprieto.isntgram.view.enums

enum class NavigationControllerValues(val screen: String) {
    // To be removed.

    // New ones
    @Deprecated("Migrate to Screen.")
    LANDING("landing"), LOGIN("login"), REGISTER("register"), MAIN("main"),

    // DEV
    DUMMY("dummy")
}