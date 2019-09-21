package com.smarttoolfactory.mymarket.orders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.smarttoolfactory.mymarket.R
import com.smarttoolfactory.mymarket.databinding.FragmentOrdersBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class OrdersFragment : DaggerFragment() {


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var taskListViewModel: OrdersViewModel

    private lateinit var dataBinding: FragmentOrdersBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        taskListViewModel =
            ViewModelProviders.of(activity!!, viewModelFactory).get(OrdersViewModel::class.java)


        dataBinding =
            DataBindingUtil.inflate<FragmentOrdersBinding>(
                inflater,
                R.layout.fragment_orders,
                container,
                false
            )

        return dataBinding.root

    }

}
