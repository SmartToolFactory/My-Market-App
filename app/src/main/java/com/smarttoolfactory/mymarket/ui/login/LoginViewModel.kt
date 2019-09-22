package com.smarttoolfactory.mymarket.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.smarttoolfactory.mymarket.ui.orders.OrdersFragment
import com.smarttoolfactory.mymarket.utils.SingleLiveEvent
import javax.inject.Inject


/**
 * Constructor with @Inject is required to have constructor injection for this ViewModel
 *
 * "ViewModel cannot be provided without an @Inject constructor or an @Provides-annotated method" exception occurs otherwise.
 */
class LoginViewModel @Inject constructor() : ViewModel() {

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
         * Authentication failed
         */
        INVALID_AUTHENTICATION
    }


    val authenticationState = MutableLiveData<AuthenticationState>()

    var userName = MutableLiveData<String>()
    var userPassword = MutableLiveData<String>()

    /**
     * Remember users selection
     */
    var rememberMe = SingleLiveEvent<Boolean>()

    /**
     * Event to go [OrdersFragment]. When triggered changes fragment
     */
    var goToOrderScreen = SingleLiveEvent<Boolean>()


    var username: String

    init {
        // User is always unauthenticated when MainActivity is launched
        authenticationState.value = AuthenticationState.UNAUTHENTICATED
        username = ""
    }

    fun refuseAuthentication() {
        authenticationState.value = AuthenticationState.UNAUTHENTICATED
    }

    fun authenticate(username: String, password: String) {
        if (passwordIsValidForUsername(username, password)) {
            this.username = username
            authenticationState.value = AuthenticationState.AUTHENTICATED
        } else {
            authenticationState.value = AuthenticationState.INVALID_AUTHENTICATION
        }
    }

    // TODO HAVE REAL Authentication
    private fun passwordIsValidForUsername(username: String, password: String): Boolean {
        return true
    }

}