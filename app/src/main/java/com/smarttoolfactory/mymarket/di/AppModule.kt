package com.smarttoolfactory.mymarket.di


import android.app.Application
import androidx.room.Room
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.smarttoolfactory.mymarket.api.OrdersApi
import com.smarttoolfactory.mymarket.constants.BASE_URL
import com.smarttoolfactory.mymarket.constants.DATABASE_NAME
import com.smarttoolfactory.mymarket.data.repository.*
import com.smarttoolfactory.mymarket.data.source.local.AppDatabase
import com.smarttoolfactory.mymarket.data.source.local.LocalOrdersDataSource
import com.smarttoolfactory.mymarket.data.source.local.dao.OrdersDao
import com.smarttoolfactory.mymarket.data.source.local.dao.UsersDao
import com.smarttoolfactory.mymarket.data.source.remote.RemoteOrdersDataSource
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
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
    @Named("remote")
    fun providesRemoteOrdersDataSource(ordersApi: OrdersApi): OrdersDataSource {
        return RemoteOrdersDataSource(ordersApi)
    }


    /*
     *** Database Injections ***
    */

    /**
     * Provide copy of database
     */
    @Singleton
    @Provides
    fun provideDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(
            application,
            AppDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    /**
     * Provide Data Access Object for querying user table
     */
    @Singleton
    @Provides
    fun provideUserDao(appDatabase: AppDatabase): UsersDao {
        return appDatabase.usersDao()
    }


    @Singleton
    @Provides
    fun provideOrdersDao(appDatabase: AppDatabase): OrdersDao {
        return appDatabase.ordersDao()
    }

    @Singleton
    @Provides
    @Named("local")
    fun providesLocalOrdersDataSource(ordersDao: OrdersDao): OrdersDataSource {
        return LocalOrdersDataSource(ordersDao)
    }

    /*
        *** Repository Injections ***
     */

    @Singleton
    @Provides
    fun provideLoginRepository(usersDao: UsersDao): LoginRepository {
        return LoginRepositoryImpl(usersDao)
    }

    @Singleton
    @Provides
    fun provideOrdersRepository(@Named("remote") webService: OrdersDataSource, @Named("local") localOrdersDataSource: OrdersDataSource): OrdersRepository {
        return OrdersRepositoryImpl(webService, localOrdersDataSource)
    }


}
