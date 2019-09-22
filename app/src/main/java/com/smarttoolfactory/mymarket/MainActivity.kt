package com.smarttoolfactory.mymarket

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.smarttoolfactory.mymarket.api.OrdersApi
import com.smarttoolfactory.mymarket.databinding.ActivityMainBinding
import com.smarttoolfactory.mymarket.ui.login.LoginViewModel
import dagger.android.support.DaggerAppCompatActivity
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

        handleToolBarVisibility()

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

            val navController = findNavController(R.id.nav_host_fragment)

            when (it) {

                LoginViewModel.AuthenticationState.AUTHENTICATED ->
                    navController.navigate(R.id.action_loginFragment_to_ordersFragment)

                LoginViewModel.AuthenticationState.INVALID_AUTHENTICATION ->
                    Toast.makeText(
                        this,
                        "Kullanıcı adı veya şifre eşleşmiyor",
                        Toast.LENGTH_SHORT
                    ).show()

                LoginViewModel.AuthenticationState.UNAUTHENTICATED ->
                    navController.navigate(R.id.nav_graph)

                LoginViewModel.AuthenticationState.LOGGED_OUT ->
                    navController.navigate(R.id.action_ordersFragment_to_loginFragment)


                else -> Toast.makeText(
                    this,
                    "Kullanıcı adı veya şifre alanını boş bırakmayın",
                    Toast.LENGTH_SHORT
                ).show()

            }
        })
    }

    private fun handleToolBarVisibility() {
        // Create Toolbar and set support action bar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        // ⚠️ This is not required for Navigation view, back arrow or hamburger button
        // ⚠️ Only Needed for displaying menu icons
        setSupportActionBar(toolbar)

        // Get NavController
        val navController = findNavController(R.id.nav_host_fragment)

        listenForNavigationEvents(navController, toolbar)
    }


    /**
     * Listen fragment changes to display or conceal Toolbar
     */
    private fun listenForNavigationEvents(navController: NavController, toolbar: Toolbar) {


        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.loginFragment) {
                toolbar?.visibility = View.GONE
            } else {
                toolbar?.visibility = View.VISIBLE
            }
        }
    }
}
