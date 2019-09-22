package com.smarttoolfactory.mymarket.data.source.local

import com.smarttoolfactory.mymarket.data.OrdersDataSource
import com.smarttoolfactory.mymarket.data.model.Order
import com.smarttoolfactory.mymarket.data.source.local.dao.OrdersDao
import io.reactivex.Observable
import javax.inject.Inject

class LocalOrdersDataSource @Inject constructor(val ordersDao: OrdersDao) : OrdersDataSource {


    override fun getOrderList(): Observable<List<Order>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}