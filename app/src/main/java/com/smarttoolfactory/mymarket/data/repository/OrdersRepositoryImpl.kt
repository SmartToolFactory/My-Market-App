package com.smarttoolfactory.mymarket.data.repository

import com.smarttoolfactory.mymarket.data.OrdersDataSource
import com.smarttoolfactory.mymarket.data.model.Order
import io.reactivex.Observable
import javax.inject.Inject

/**
 * [OrdersRepositoryImpl] is concrete implementation of Repository pattern which acts as a Persistence
 * layer with local, and remote [OrdersDataSource] providers.
 */
class OrdersRepositoryImpl @Inject constructor(
    private val webService: OrdersDataSource
) :
    OrdersRepository {
    override fun getOrderList(): Observable<List<Order>> {
        return webService.getOrderList()
    }


    // TODO Implement database features
    override fun saveOrderListToDb() {

    }

    override fun clearOrderListFromDb() {

    }

}