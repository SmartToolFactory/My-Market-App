package com.smarttoolfactory.mymarket.di


import com.smarttoolfactory.mymarket.ui.login.LoginFragment
import com.smarttoolfactory.mymarket.ui.orders.OrdersFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


/**
 * [FragmentContributorModule] is used inside [ActivityContributorModule]
 * With @ContributesAndroidInjector(modules = FragmentContributorModule.class)
 * defines which module will be used to inject objects to declared fragments
 */
@Module
abstract class FragmentContributorModule {

//    @FragmentScope
    @ContributesAndroidInjector
    abstract fun contributeLoginFragment(): LoginFragment

//    @FragmentScope
    @ContributesAndroidInjector
    abstract fun contributeOrdersFragment(): OrdersFragment
}

