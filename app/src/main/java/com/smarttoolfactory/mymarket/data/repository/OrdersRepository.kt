package com.smarttoolfactory.mymarket.data.repository

import com.smarttoolfactory.mymarket.data.model.Order
import io.reactivex.Observable

//
/**
 * [OrdersRepository] interface is used for implementing Repository pattern which provides
 * persistence layer that consists of Remote and Local data sources.
 *
 * 🔥 Also uses offline first to save orders to db and display them as required from database
 */
interface OrdersRepository {

    fun getOrdersList(): Observable<List<Order>>

    /**
     * Saves orders to Room database that retrieved from web service
     */
    fun saveOrdersListToDb()

    /**
     * Clear order list in db to add new orders
     */
    fun clearOrdersListFromDb()


}