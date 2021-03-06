package com.smarttoolfactory.mymarket.data.repository

import com.smarttoolfactory.mymarket.data.model.Order
import io.reactivex.Completable
import io.reactivex.Observable

//
/**
 * [OrdersRepository] interface is used for implementing Repository pattern which provides
 * persistence layer that consists of Remote and Local data sources.
 *
 * 🔥 Also uses offline first to save orders to db and display them as required from database
 */
interface OrdersRepository {

    fun getOrderList(): Observable<List<Order>?>

    /**
     * Saves orders to Room database that retrieved from web service
     */
    fun saveOrderListToDb(orders: List<Order>): Completable

//    /**
//     * Clear order list in db to add new orders
//     */
//    fun clearOrderListFromDb()


}