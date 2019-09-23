package com.smarttoolfactory.mymarket.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.smarttoolfactory.mymarket.data.model.Order
import io.reactivex.Completable
import io.reactivex.Flowable

/**
 * Data Access Object for the orders table.
 */
@Dao
interface OrdersDao : BaseDao<Order> {

    /**
     * Get orders list from database
     */
    @Query("SELECT * FROM orders")
    fun getOrders(): Flowable<List<Order>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveOrders(entity: List<Order>): Completable


}