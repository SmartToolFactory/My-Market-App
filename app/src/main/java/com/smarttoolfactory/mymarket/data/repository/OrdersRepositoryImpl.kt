package com.smarttoolfactory.mymarket.data.repository

import com.smarttoolfactory.mymarket.data.model.Order
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * [OrdersRepositoryImpl] is concrete implementation of Repository pattern which acts as a Persistence
 * layer with local, and remote [OrdersDataSource] providers.
 */
class OrdersRepositoryImpl @Inject constructor(
    private val webService: OrdersDataSource,
    private val localDataSource: OrdersDataSource
) : OrdersRepository {


    /**
     * Check database first if there are any saved
     */
    override fun getOrderList(): Observable<List<Order>?> {

        return localDataSource
            .getOrderList()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .flatMap {
                //
                if (it == null || it.size == 0) {
                    webService.getOrderList().map {
                        //  Save orders to database
                        it?.let {
                            saveOrderListToDb(it).subscribe()
                        }
                        it
                    }
                } else {
                    Observable.just(it)
                }
            }


    }


    override fun saveOrderListToDb(orders: List<Order>): Completable {
        return localDataSource.saveOrderList(orders)
    }


}