package com.smarttoolfactory.mymarket.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.smarttoolfactory.mymarket.data.model.User
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

    /**
     * Authentication state
     */
    val authenticationState = SingleLiveEvent<AuthenticationState>()

    var userName = MutableLiveData<String>()
    var userPassword = MutableLiveData<String>()

    /**
     * Remember users selection
     */
    var rememberMe = MutableLiveData<Boolean>()


    init {
        rememberMe.value = false
        // User is always unauthenticated when MainActivity is launched
//        authenticationState.value = AuthenticationState.AUTHENTICATED

        val disposable = loginUseCase.getRegisteredUser()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {

                if (it != null && it.rememberUser == true) {
                    authenticationState.value = AuthenticationState.AUTHENTICATED
                }
            }

        disposables.add(disposable)

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
                    authenticationState.value = AuthenticationState.INVALID_AUTHENTICATION
                })


        disposables.add(disposable)

    }


    fun logOut() {

        val disposable = loginUseCase.getRegisteredUser()
            .flatMap {
                loginUseCase.logOut(it)
            }

            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

            .subscribe(
                {
                    authenticationState.value = AuthenticationState.LOGGED_OUT
                },
                {
                    authenticationState.value = AuthenticationState.LOGGED_OUT
                })


        userName.value = ""
        userPassword.value = ""


        disposables.add(disposable)
    }

    fun mockTestUser() {
        loginUseCase.registerMockUser()
    }


}