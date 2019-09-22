package com.smarttoolfactory.mymarket.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.smarttoolfactory.mymarket.constants.DATABASE_VERSION
import com.smarttoolfactory.mymarket.data.model.Order
import com.smarttoolfactory.mymarket.data.model.User
import com.smarttoolfactory.mymarket.data.source.local.dao.OrdersDao
import com.smarttoolfactory.mymarket.data.source.local.dao.UsersDao
import com.smarttoolfactory.mymarket.data.source.local.typeconvertors.RoomTypeConverter

/**
 * Room Database that contains the [Order], and [User] entity tables.
 *
 */
@Database(entities = [Order::class, User::class], version = DATABASE_VERSION, exportSchema = false)

@TypeConverters(RoomTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun ordersDao(): OrdersDao
    abstract fun usersDao(): UsersDao
}

