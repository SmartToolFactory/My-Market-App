package com.smarttoolfactory.mymarket

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.smarttoolfactory.mymarket.databinding.ActivityMainBinding
import com.smarttoolfactory.mymarket.login.LoginFragment
import com.smarttoolfactory.mymarket.login.LoginViewModel
import com.smarttoolfactory.mymarket.orders.OrdersFragment
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var loginViewModel: LoginViewModel

    /**
     * Data-binding for this activity
     */
    private lateinit var dataBinding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loginViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel::class.java)


        bindViews()

        subscribeLogin()
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

        // Set up Fragment
        val currentFragment = LoginFragment.newInstance()

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.content_frame, currentFragment)
            .commit()

    }


    /**
     * Listen login-register process, if
     */
    private fun subscribeLogin() {

        loginViewModel.login.observe(this, Observer {
            it?.takeIf {
                true
            }.run {

                // Set up Fragment
                val currentFragment = OrdersFragment.newInstance()

                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.content_frame, currentFragment)
                    .commit()

            }
        })
    }
}
