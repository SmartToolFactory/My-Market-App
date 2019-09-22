package com.smarttoolfactory.mymarket.domain

import com.smarttoolfactory.mymarket.base.BaseUseCase
import com.smarttoolfactory.mymarket.data.model.Order
import com.smarttoolfactory.mymarket.data.repository.OrdersRepository
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * UseCase for getting orders from repository
 */
class GetOrdersUseCase @Inject constructor(private val repository: OrdersRepository) : BaseUseCase() {

    fun getOrdersList(): Observable<List<Order>> {
        return repository.getOrdersList()
    }

    override fun dispose() {

    }

}