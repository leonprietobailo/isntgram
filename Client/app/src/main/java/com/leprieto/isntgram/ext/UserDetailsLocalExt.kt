package com.leprieto.isntgram.ext

import com.leprieto.isntgram.model.api.UserDto
import com.leprieto.isntgram.model.db.UserDetailsLocal

fun UserDetailsLocal.toRemote(): UserDto {
    return UserDto(this.id, "", null)
}