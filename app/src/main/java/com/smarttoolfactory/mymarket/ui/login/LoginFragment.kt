package com.smarttoolfactory.mymarket.ui.login

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.smarttoolfactory.mymarket.R
import com.smarttoolfactory.mymarket.base.BaseFragment
import com.smarttoolfactory.mymarket.databinding.FragmentLoginBinding

class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    private lateinit var loginViewModel: LoginViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 🔥 this is a shared viewModel between MainActivity and LoginFragment
        loginViewModel =
            ViewModelProviders.of(activity!!, viewModelFactory).get(LoginViewModel::class.java)

        dataBinding.viewModel = loginViewModel


    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_login
    }


    companion object {

        fun newInstance(): LoginFragment {

            val args = Bundle()

            val fragment = LoginFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
