package com.smarttoolfactory.mymarket.domain

import com.smarttoolfactory.mymarket.base.BaseUseCase
import com.smarttoolfactory.mymarket.data.model.Order
import com.smarttoolfactory.mymarket.data.model.OrderListItem
import com.smarttoolfactory.mymarket.data.repository.OrdersRepository
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * UseCase for getting orders from repository
 */
class GetOrderListUseCase @Inject constructor(private val repository: OrdersRepository) :
    BaseUseCase() {

    fun getOrderList(): Observable<List<Order>> {
        return repository.getOrderList()
            .doOnNext {
                println("🥶 List $it")
            }
    }


    fun getOrderItemList(): Observable<List<OrderListItem>> {

        return repository.getOrderList()
            .map {

                val orderListItems = mutableListOf<OrderListItem>()

                it?.forEach {
                    val orderListItem = OrderListItem(it)
                    orderListItems.add(orderListItem)
                }

                orderListItems
            }
    }

    override fun dispose() {

    }


    private fun geMonthName(monthNum: String):String {
    return    when(monthNum) {
            "01"-> "Ocak"
            "02"-> "Şubat"
            "03"-> "Mart"
            "04"-> "Nisan"
            "05"-> "Mayıs"
            "06"-> "Haziran"
            "07"-> "Temmuz"
            "08"-> "Ağustos"
            "09"-> "Eylül"
            "10"-> "Ekim"
            "11"-> "Kasım"
            "12"-> "Aralık"
        else -> "Ocak"
    }

    }

}