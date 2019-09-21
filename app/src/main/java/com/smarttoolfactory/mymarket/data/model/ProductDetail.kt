package com.smarttoolfactory.mymarket.data.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductDetail(
    @SerializedName("orderDetail")
    @Expose
    var orderDetail: String? = null,
    @SerializedName("summaryPrice")
    @Expose
    var summaryPrice: Double? = null
) : Parcelable