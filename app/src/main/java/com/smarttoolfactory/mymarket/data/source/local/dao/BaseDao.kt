package com.smarttoolfactory.mymarket.data.source.local.dao


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Update
import io.reactivex.Completable

/**
 * Base Data Access Object interface for generic type T. Contains methods for common functions that does not include
 * queries with @Query
 * @param T generic type of Object to be inserted, updated or deleted
</T> */
@Dao
interface BaseDao<T> {


    @Insert(onConflict = REPLACE)
    fun insert(entity: T): Completable

    @Update
    fun update(entity: T): Completable

    @Delete
    fun delete(entity: T): Completable


}
