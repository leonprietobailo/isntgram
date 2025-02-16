package com.leprieto.isntgram.ext

import com.leprieto.isntgram.model.api.UserDetails
import com.leprieto.isntgram.model.db.UserDetailsLocal

fun UserDetailsLocal.toRemote(): UserDetails {
    return UserDetails(this.id, "", null)
}