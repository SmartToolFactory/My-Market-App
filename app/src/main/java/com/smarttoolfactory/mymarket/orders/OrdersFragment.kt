package com.smarttoolfactory.mymarket.orders

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.smarttoolfactory.mymarket.R
import com.smarttoolfactory.mymarket.base.BaseFragment
import com.smarttoolfactory.mymarket.databinding.FragmentOrdersBinding
import com.smarttoolfactory.mymarket.login.LoginFragment

class OrdersFragment : BaseFragment<FragmentOrdersBinding>() {

    private lateinit var ordersViewModel: OrdersViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ordersViewModel =
            ViewModelProviders.of(activity!!, viewModelFactory).get(OrdersViewModel::class.java)

        dataBinding?.viewModel = ordersViewModel
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_orders
    }

    companion object {

        fun newInstance(): OrdersFragment {

            val args = Bundle()

            val fragment = OrdersFragment()
            fragment.arguments = args
            return fragment
        }
    }

}
