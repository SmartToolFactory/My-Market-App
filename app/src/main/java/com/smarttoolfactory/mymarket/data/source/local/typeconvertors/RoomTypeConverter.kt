package com.smarttoolfactory.mymarket.data.source.local.typeconvertors

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.smarttoolfactory.mymarket.data.model.ProductDetail


/**
 * Converter is required to insert [ProductDetail] to database as String
 */
class RoomTypeConverter {

    @TypeConverter
    fun fromProductDetail(productDetail: ProductDetail): String? {
        return Gson().toJson(productDetail)
    }

    @TypeConverter
    fun toProductDetail(json: String): ProductDetail? {
        val listType = object : TypeToken<ProductDetail>() {}.type
        return Gson().fromJson(json, listType)
    }


}