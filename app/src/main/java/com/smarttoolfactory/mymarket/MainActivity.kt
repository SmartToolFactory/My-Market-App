package com.smarttoolfactory.mymarket

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.smarttoolfactory.mymarket.api.OrdersApi
import com.smarttoolfactory.mymarket.databinding.ActivityMainBinding
import com.smarttoolfactory.mymarket.login.LoginViewModel
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.lang.Exception
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var ordersApi: OrdersApi

    private lateinit var loginViewModel: LoginViewModel

    /**
     * Data-binding for this Activity
     */
    private lateinit var dataBinding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loginViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel::class.java)


        bindViews()

        subscribeLoginState()

    }

    /**
     * Set contentView via data-binding, toolbar and create fragments and set UI related actions and elements
     */
    private fun bindViews() {

        // Set data-binding
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // Set Toolbar
        val toolbar = dataBinding.toolbar
        setSupportActionBar(toolbar)


    }


    /**
     * Listen Authentication Status of the User
     */
    private fun subscribeLoginState() {

        loginViewModel.authenticationState.observe(this, Observer {

            //            when (it) {
//                LoginViewModel.AuthenticationState.AUTHENTICATED -> TODO()
//                LoginViewModel.AuthenticationState.INVALID_AUTHENTICATION -> TODO()
//                LoginViewModel.AuthenticationState.UNAUTHENTICATED -> TODO()
//            }
        })
    }
}
