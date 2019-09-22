package com.smarttoolfactory.mymarket.domain

import com.smarttoolfactory.mymarket.base.BaseUseCase
import com.smarttoolfactory.mymarket.ui.login.LoginViewModel
import io.reactivex.Single
import javax.inject.Inject

/**
 * UseCase for logging user in or out
 */
class LoginUseCase @Inject constructor() : BaseUseCase() {


    fun logIn(
        name: String?,
        password: String?,
        rememberMe: Boolean?
    ): Single<LoginViewModel.AuthenticationState> {

        return when {

            // Empty Fields
            isEmpty(name, password)
            -> Single.just(LoginViewModel.AuthenticationState.EMPTY_FIELDS)

            // Authentication is successful
            isValidPassword(name, password)
            -> Single.just(LoginViewModel.AuthenticationState.AUTHENTICATED)

            // Authentication failed
            else -> Single.just(LoginViewModel.AuthenticationState.INVALID_AUTHENTICATION)
        }

    }

    fun logOut(): Single<LoginViewModel.AuthenticationState> {
        return Single.just(LoginViewModel.AuthenticationState.LOGGED_OUT)
    }

    private fun isEmpty(name: String?, password: String?): Boolean {
        return (name == null || name.isEmpty() ||
                password == null || password.isEmpty())
    }

    /**
     * Get user info from database
     */
    private fun isValidPassword(name: String?, password: String?): Boolean {
        return name == "kariyer" && password == "2019ADev"
    }


    override fun dispose() {

    }


}