package com.smarttoolfactory.mymarket.data.source.local.dao

import androidx.room.Dao
import com.smarttoolfactory.mymarket.data.model.User

/**
 * Data Access Object for the users table.
 */
@Dao
interface UsersDao : BaseDao<User> {



}