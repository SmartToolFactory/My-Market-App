package com.smarttoolfactory.mymarket.orders

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.smarttoolfactory.mymarket.R
import com.smarttoolfactory.mymarket.base.BaseFragment
import com.smarttoolfactory.mymarket.data.model.Order
import com.smarttoolfactory.mymarket.databinding.FragmentOrdersBinding

class OrdersFragment : BaseFragment<FragmentOrdersBinding>() {

    private lateinit var ordersViewModel: OrdersViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ordersViewModel =
            ViewModelProviders.of(activity!!, viewModelFactory).get(OrdersViewModel::class.java)

        dataBinding.viewModel = ordersViewModel

        subscribeOrdersList()
    }


    /**
     * Listen changes of [Order]s list of ViewModel. When there are orders display them via [RecyclerView]
     */
    private fun subscribeOrdersList() {

        ordersViewModel.ordersList.observe(this, Observer {
            it?.apply {

                // TODO Create RecyclerView Adapter do display items
                val sb = StringBuilder()

                forEach {
                    sb.append("${it.orderName}\n")
                }

                dataBinding.tvOrders.text = sb.toString()

            }
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
