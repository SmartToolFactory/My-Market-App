package com.smarttoolfactory.mymarket.data.source.remote

import com.smarttoolfactory.mymarket.api.OrdersApi
import com.smarttoolfactory.mymarket.data.repository.OrdersDataSource
import com.smarttoolfactory.mymarket.data.model.Order
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Remote data source that retrieves orders from web service
 */
class RemoteOrdersDataSource @Inject constructor(
    private val ordersApi: OrdersApi) :
    OrdersDataSource {

    /**
     * Order list is not saved to remote source
     */
    override fun saveOrderList(orders: List<Order>): Completable {
        return Completable.complete()
    }

    override fun getOrderList(): Observable<List<Order>?> {
        return ordersApi.getOrderList()
    }

}