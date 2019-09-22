package com.smarttoolfactory.mymarket.di


import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.smarttoolfactory.mymarket.api.OrdersApi
import com.smarttoolfactory.mymarket.constants.BASE_URL
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module(includes = [ViewModelModule::class])
class AppModule {


    /*
      *** REST Api Injections ***
   */

    @Singleton
    @Provides
    fun provideOrdersApi(): OrdersApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(OrdersApi::class.java)
    }

    /*
        *** Database Injections ***
     */


}
