package com.smarttoolfactory.mymarket.data.repository

import com.smarttoolfactory.mymarket.data.model.User
import io.reactivex.Maybe

interface LoginRepository {

    fun getRegisteredUser(): Maybe<User>

    fun logIn(
        name: String?,
        password: String?,
        rememberMe: Boolean?
    ): Maybe<Long>

    fun logOut(user: User): Maybe<Int>

    fun registerUser(user: User): Maybe<Long>
}