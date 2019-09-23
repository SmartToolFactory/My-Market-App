package com.smarttoolfactory.mymarket.data.source.local

import com.smarttoolfactory.mymarket.data.repository.OrdersDataSource
import com.smarttoolfactory.mymarket.data.model.Order
import com.smarttoolfactory.mymarket.data.source.local.dao.OrdersDao
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class LocalOrdersDataSource @Inject constructor(private val ordersDao: OrdersDao) :
    OrdersDataSource {

    override fun saveOrderList(orders: List<Order>): Completable {
      return  ordersDao.saveOrders(orders)
    }

    override fun getOrderList(): Observable<List<Order>?> {
        return ordersDao.getOrders().toObservable()
    }


}