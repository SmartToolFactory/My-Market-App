package com.smarttoolfactory.mymarket.di


import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.smarttoolfactory.mymarket.api.OrdersApi
import com.smarttoolfactory.mymarket.constants.BASE_URL
import com.smarttoolfactory.mymarket.data.OrdersDataSource
import com.smarttoolfactory.mymarket.data.repository.OrdersRepository
import com.smarttoolfactory.mymarket.data.repository.OrdersRepositoryImpl
import com.smarttoolfactory.mymarket.data.source.remote.RemoteOrdersDataSource
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

    @Singleton
    @Provides
    fun providesOrdersDataSource(ordersApi: OrdersApi): OrdersDataSource {
        return RemoteOrdersDataSource(ordersApi)
    }


    /*
     *** Database Injections ***
    */

    /*
        *** Repository Injections ***
     */

    @Singleton
    @Provides
    fun provideOrdersRepository(webService: OrdersDataSource): OrdersRepository {
        return OrdersRepositoryImpl(webService)
    }


}
