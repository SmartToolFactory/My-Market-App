package com.smarttoolfactory.mymarket.data

import com.smarttoolfactory.mymarket.data.model.Order
import io.reactivex.Observable

interface OrdersDataSource {

    fun getOrderList(): Observable<List<Order>>


}