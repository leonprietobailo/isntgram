package com.leprieto.isntgram.ext

import com.leprieto.isntgram.model.api.User
import com.leprieto.isntgram.model.db.UserDetailsLocal

fun UserDetailsLocal.toRemote(): User {
    return User(this.id, "", null)
}