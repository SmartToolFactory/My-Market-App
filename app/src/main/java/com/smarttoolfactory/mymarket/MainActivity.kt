package com.smarttoolfactory.mymarket

import android.content.Context
import android.net.wifi.WifiManager
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.content.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.smarttoolfactory.mymarket.api.OrdersApi
import com.smarttoolfactory.mymarket.constants.IS_FIRST_RUN_KEY
import com.smarttoolfactory.mymarket.databinding.ActivityMainBinding
import com.smarttoolfactory.mymarket.ui.login.LoginViewModel
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory


    private lateinit var loginViewModel: LoginViewModel

    /**
     * Data-binding for this Activity
     */
    private lateinit var dataBinding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loginViewModel =
            ViewModelProvider(this, viewModelFactory).get(LoginViewModel::class.java)

        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this)

        val isFirsRun  = sharedPrefs.getBoolean(IS_FIRST_RUN_KEY, true)


        // Create mock user first time the app is run
        if (isFirsRun) {
            val editor = sharedPrefs.edit()

            // Create a mock user with name = kariyer, password = 2019ADev, remember = false
            loginViewModel.mockTestUser()

            editor.putBoolean(IS_FIRST_RUN_KEY, false)
            editor.apply()
        }


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
                    // Goes to Orders fragment after animation
                    navController.navigate(R.id.orders_dest)

                // Removed this, NOT USED in this example
                LoginViewModel.AuthenticationState.UNAUTHENTICATED ->
                    navController.navigate(R.id.main_navigation)

                // User touched log out button in orders fragment
                LoginViewModel.AuthenticationState.LOGGED_OUT ->
                    navController.navigate(R.id.action_ordersFragment_to_loginFragment)

                // User name or/and password incorrect
                LoginViewModel.AuthenticationState.INVALID_AUTHENTICATION ->
                    Toast.makeText(
                        this,
                        "Kullanıcı adı veya şifre eşleşmiyor",
                        Toast.LENGTH_SHORT
                    ).show()

                // User name or/and password empty
                else -> Toast.makeText(
                    this,
                    "Kullanıcı adı veya şifre alanını boş bırakmayın!",
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
            if (destination.id == R.id.login_dest) {
                toolbar.visibility = View.GONE
            } else {
                toolbar.visibility = View.VISIBLE
            }
        }
    }
}
