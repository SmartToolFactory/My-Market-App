package com.smarttoolfactory.mymarket.login

import androidx.lifecycle.ViewModel
import com.smarttoolfactory.mymarket.utils.SingleLiveEvent
import javax.inject.Inject


/**
 * Constructor with @Inject is required to have constructor injection for this ViewModel
 *
 * "ViewModel cannot be provided without an @Inject constructor or an @Provides-annotated method" exception occurs otherwise.
 */
class LoginViewModel @Inject constructor() : ViewModel() {

    val login = SingleLiveEvent<Boolean>()


    fun onLogin() {
        login.value = true
    }
}