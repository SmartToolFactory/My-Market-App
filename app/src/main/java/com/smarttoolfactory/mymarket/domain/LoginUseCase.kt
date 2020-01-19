package com.smarttoolfactory.mymarket.domain

import com.smarttoolfactory.mymarket.base.BaseUseCase
import com.smarttoolfactory.mymarket.constants.MOCK_USER_NAME
import com.smarttoolfactory.mymarket.constants.MOCK_USER_PASSWORD
import com.smarttoolfactory.mymarket.data.model.User
import com.smarttoolfactory.mymarket.data.repository.LoginRepository
import com.smarttoolfactory.mymarket.ui.login.LoginViewModel
import io.reactivex.Maybe
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * UseCase for logging user in or out
 */
class LoginUseCase @Inject constructor(private val loginRepository: LoginRepository) :
    BaseUseCase() {


    fun getRegisteredUser(): Maybe<User> {
        return loginRepository.getRegisteredUser()
    }

    fun logIn(
        name: String?,
        password: String?,
        rememberMe: Boolean?
    ): Single<LoginViewModel.AuthenticationState> {

        return when {

            // Empty Fields
            isEmpty(name, password)
            -> Single.just(LoginViewModel.AuthenticationState.EMPTY_FIELDS)

            // Authentication failed
            else ->

                loginRepository.getRegisteredUser().flatMapSingle {

                    if (it.name == name && it.password == password) {

                        // Fields match with user on database, update user with RememberMe flag
                        loginRepository.logIn(name, password, rememberMe).flatMapSingle {
                            Single.just(LoginViewModel.AuthenticationState.AUTHENTICATED)
                        }


                    } else {
                        Single.just(LoginViewModel.AuthenticationState.INVALID_AUTHENTICATION)
                    }

                }
        }
    }

    /**
     * Logs out user and deletes register user. But for this assignment 1 user identity is
     * kept in db for mock
     */
    fun logOut(user: User): Maybe<Int> {
        user.rememberUser = false
        return loginRepository.logOut(user)
    }

    private fun isEmpty(name: String?, password: String?): Boolean {
        return (name == null || name.isEmpty() ||
                password == null || password.isEmpty())
    }


    /**
     * Set a mock user for this assignment
     */
    fun registerMockUser() {
        val user = User(name = MOCK_USER_NAME, password = MOCK_USER_PASSWORD, rememberUser = false)
        loginRepository.registerUser(user)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe()
    }


    override fun dispose() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
