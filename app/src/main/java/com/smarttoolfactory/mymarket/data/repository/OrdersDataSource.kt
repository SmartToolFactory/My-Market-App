package com.smarttoolfactory.mymarket.data.repository

import com.smarttoolfactory.mymarket.data.model.Order
import io.reactivex.Completable
import io.reactivex.Observable

interface OrdersDataSource {


    fun getOrderList(): Observable<List<Order>?>

    fun saveOrderList(orders: List<Order>) : Completable


}