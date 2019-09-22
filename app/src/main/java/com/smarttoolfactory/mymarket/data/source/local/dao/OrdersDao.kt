package com.smarttoolfactory.mymarket.data.source.local.dao

import androidx.room.Dao
import com.smarttoolfactory.mymarket.data.model.Order

/**
 * Data Access Object for the orders table.
 */
@Dao
interface OrdersDao : BaseDao<Order> {


}