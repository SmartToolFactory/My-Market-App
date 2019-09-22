package com.smarttoolfactory.mymarket.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey val key: Int = 0,
    var name: String,
    var password:String
)