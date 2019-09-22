package com.smarttoolfactory.mymarket.ui.orders

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.smarttoolfactory.mymarket.R
import com.smarttoolfactory.mymarket.base.BaseFragment
import com.smarttoolfactory.mymarket.databinding.FragmentOrdersBinding
import com.smarttoolfactory.mymarket.ui.login.LoginViewModel
import com.smarttoolfactory.mymarket.view.adapter.OrderListAdapter

class OrdersFragment : BaseFragment<FragmentOrdersBinding>() {

    private lateinit var ordersViewModel: OrdersViewModel

    private lateinit var loginViewModel: LoginViewModel

    private lateinit var orderListAdapter: OrderListAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // LoginViewModel is reqired to log out when user touches log out button
        loginViewModel =
            ViewModelProviders.of(activity!!, viewModelFactory).get(LoginViewModel::class.java)


        dataBinding.loginViewModel = loginViewModel


        ordersViewModel =
            ViewModelProviders.of(activity!!, viewModelFactory).get(OrdersViewModel::class.java)

        dataBinding.viewModel = ordersViewModel

        // Set RecyclerView layout manager, and adapter
        orderListAdapter = OrderListAdapter(ordersViewModel)

        val linearLayoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        dataBinding.recyclerView.apply {
            this.layoutManager = linearLayoutManager
            // Set RecyclerViewAdapter
            this.adapter = orderListAdapter
        }

//        subscribeOrderList()
        subscribeExpandList()

    }


//    /**
//     * Listen changes of [Order]s list of ViewModel. When there are orders display them via [RecyclerView]
//     */
//    private fun subscribeOrderList() {
//        ordersViewModel.items.observe(this, Observer {
//
//        })
//    }

    private fun subscribeExpandList() {
        ordersViewModel.expandPosition.observe(this, Observer {
            orderListAdapter.notifyItemChanged(it)
        })
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
