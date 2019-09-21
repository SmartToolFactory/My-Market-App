package com.smarttoolfactory.mymarket.di


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.smarttoolfactory.mymarket.login.LoginViewModel
import com.smarttoolfactory.mymarket.orders.OrdersViewModel
import com.smarttoolfactory.mymarket.viewmodel.CustomViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 *
 * This module is used for retrieving ViewModels using [CustomViewModelFactory], viewModels are put into map
 * via @IntoMap and @ViewModelKey annotations
 *
 * IntoMap creates a key-value pair to get relevant ViewModel. Key is the class name of ViewModel
 * and value is the ViewModel itself
 */
@Module
abstract class ViewModelModule {


    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(loginViewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(OrdersViewModel::class)
    abstract fun bindOrdersViewModel(ordersViewModel: OrdersViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: CustomViewModelFactory): ViewModelProvider.Factory
}
