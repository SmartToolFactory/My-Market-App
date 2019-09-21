package com.smarttoolfactory.mymarket.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class OrdersModel(
    var orders: List<Order>? = null
) : Parcelable