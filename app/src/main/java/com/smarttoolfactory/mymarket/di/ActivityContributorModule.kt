package com.smarttoolfactory.mymarket.di


import com.smarttoolfactory.mymarket.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityContributorModule {

    /**
    Defines which fragments will be used by [MainActivity]
     */
    @ContributesAndroidInjector(modules = [FragmentContributorModule::class])
    abstract fun contributeMainActivity(): MainActivity


}
