package com.smarttoolfactory.mymarket.data.source.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.smarttoolfactory.mymarket.data.model.User
import io.reactivex.Maybe

/**
 * Data Access Object for the users table.
 */
@Dao
interface UsersDao : BaseDao<User> {

    @Query("DELETE FROM users")
    fun deleteAll(): Maybe<Int>

    @Query("SELECT * FROM users")
    fun getUser(): Maybe<User>

}