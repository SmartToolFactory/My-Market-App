package com.smarttoolfactory.mymarket.domain

import com.smarttoolfactory.mymarket.base.BaseUseCase
import com.smarttoolfactory.mymarket.data.model.Order
import com.smarttoolfactory.mymarket.data.model.OrderListItem
import com.smarttoolfactory.mymarket.data.repository.OrdersRepository
import io.reactivex.Observable
import javax.inject.Inject

/**
 * UseCase for getting orders from repository
 */
class GetOrderListUseCase @Inject constructor(private val repository: OrdersRepository) : BaseUseCase() {

    fun getOrderList(): Observable<List<OrderListItem>> {
        return repository.getOrderList()
            .map {

                val list = listOf<OrderListItem>()



             val map = HashMap<String, String>()

                


                list

            }
    }

    override fun dispose() {

    }

}