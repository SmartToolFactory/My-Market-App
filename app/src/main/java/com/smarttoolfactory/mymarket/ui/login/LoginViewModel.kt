package com.smarttoolfactory.mymarket.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.smarttoolfactory.mymarket.domain.LoginUseCase
import com.smarttoolfactory.mymarket.utils.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


/**
 * Constructor with @Inject is required to have constructor injection for this ViewModel
 *
 * "ViewModel cannot be provided without an @Inject constructor or an @Provides-annotated method" exception occurs otherwise.
 */
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    enum class AuthenticationState {
        /**
         * Initial state, the user needs to authenticate
         */
        UNAUTHENTICATED,
        /**
         * The user has authenticated successfully
         */
        AUTHENTICATED,

        /**
         *
         */
        EMPTY_FIELDS,
        /**
         * Authentication failed
         */
        INVALID_AUTHENTICATION,
        /**
         * User logged out
         */
        LOGGED_OUT
    }

    private val disposables = CompositeDisposable()

    val authenticationState = SingleLiveEvent<AuthenticationState>()

    var userName = MutableLiveData<String>()
    var userPassword = MutableLiveData<String>()

    /**
     * Remember users selection
     */
    var rememberMe = MutableLiveData<Boolean>()


    init {
        // User is always unauthenticated when MainActivity is launched
        authenticationState.value = AuthenticationState.UNAUTHENTICATED

    }

    private fun refuseAuthentication() {
        authenticationState.value = AuthenticationState.UNAUTHENTICATED
    }

    /**
     * Check for user name and password authentication
     */
    fun authenticate() {
        val disposable = loginUseCase.logIn(userName.value, userPassword.value, rememberMe.value)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                authenticationState.value = it
            },
                {
                    authenticationState.value = AuthenticationState.UNAUTHENTICATED
                })


        disposables.add(disposable)

    }

    fun logOut() {
        loginUseCase.logOut()
    }


}