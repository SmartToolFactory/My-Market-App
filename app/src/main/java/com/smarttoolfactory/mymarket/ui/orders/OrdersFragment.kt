package com.smarttoolfactory.mymarket.ui.orders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.smarttoolfactory.mymarket.R
import com.smarttoolfactory.mymarket.base.BaseFragment
import com.smarttoolfactory.mymarket.data.model.Order
import com.smarttoolfactory.mymarket.databinding.FragmentOrdersBinding
import com.smarttoolfactory.mymarket.view.adapter.OrderListAdapter

class OrdersFragment : BaseFragment<FragmentOrdersBinding>() {

    private lateinit var ordersViewModel: OrdersViewModel



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ordersViewModel =
            ViewModelProviders.of(activity!!, viewModelFactory).get(OrdersViewModel::class.java)

        dataBinding.viewModel = ordersViewModel

        // Set RecyclerView layout manager, adapter, and scroll listener for infinite scrolling
        val linearLayoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        dataBinding.recyclerView.apply {
            this.layoutManager = linearLayoutManager
            this.adapter = OrderListAdapter(ordersViewModel)
        }

        subscribeOrderList()
    }


    /**
     * Listen changes of [Order]s list of ViewModel. When there are orders display them via [RecyclerView]
     */
    private fun subscribeOrderList() {

        ordersViewModel.items.observe(this, Observer {

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
