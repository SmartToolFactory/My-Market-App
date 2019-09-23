package com.smarttoolfactory.mymarket.data.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


/**
 * Order data class which contains [ProductDetail]
 * Indice is set to create uniqie orders to prevent conflicts
 */

@Entity(
    tableName = "orders",
    indices = arrayOf(Index(value = ["date", "month", "orderName"], unique = true))
)
data class Order(

    @PrimaryKey(autoGenerate = true) val id: Int = 0,

    @SerializedName("date")
    @Expose
    var date: String? = null,
    @SerializedName("month")
    @Expose
    var month: String? = null,
    @SerializedName("marketName")
    @Expose
    var marketName: String? = null,
    @SerializedName("orderName")
    @Expose
    var orderName: String? = null,
    @SerializedName("productPrice")
    @Expose
    var productPrice: Double? = null,
    @SerializedName("productState")
    @Expose
    var productState: String? = null,
    @SerializedName("productDetail")
    @Expose
    var productDetail: ProductDetail? = null


)

