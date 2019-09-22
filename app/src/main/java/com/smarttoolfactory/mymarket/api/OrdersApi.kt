package com.smarttoolfactory.mymarket.api

import com.smarttoolfactory.mymarket.data.model.Order
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET

/**
 * Retrofit interface to get Order list from REST api
 */
interface OrdersApi {


    /**
     * Gets list of [Order]s from REST api
     *
     * Used @GET(".") to declare that your final URL is the same as your base URL.
     *
     * https://stackoverflow.com/questions/40062564/retrofit-error-missing-either-get-url-or-url-parameter
     */
    @GET(".")
    fun getOrderList(): Observable<List<Order>>


}
