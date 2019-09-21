package com.smarttoolfactory.mymarket.data.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Order(

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
) : Parcelable
