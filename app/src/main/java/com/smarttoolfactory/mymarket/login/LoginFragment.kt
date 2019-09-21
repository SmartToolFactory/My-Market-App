package com.smarttoolfactory.mymarket.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.smarttoolfactory.mymarket.R
import com.smarttoolfactory.mymarket.databinding.FragmentLoginBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class LoginFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var loginViewModel: LoginViewModel

    private lateinit var dataBinding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        loginViewModel =
            ViewModelProviders.of(activity!!, viewModelFactory).get(LoginViewModel::class.java)


        dataBinding =
            DataBindingUtil.inflate<FragmentLoginBinding>(
                inflater,
                R.layout.fragment_login,
                container,
                false
            )

        return dataBinding.root

    }

}
