package com.smarttoolfactory.mymarket.orders

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.smarttoolfactory.mymarket.data.model.Order
import com.smarttoolfactory.mymarket.domain.GetOrdersUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


/**
 * Constructor with @Inject is required to have constructor injection for this ViewModel
 *
 * "ViewModel cannot be provided without an @Inject constructor or an @Provides-annotated method" exception occurs otherwise.
 */
class OrdersViewModel @Inject constructor(private val getOrdersUseCase: GetOrdersUseCase) :
    ViewModel() {


    private val disposables = CompositeDisposable()

    private val _ordersList = MutableLiveData<List<Order>>()
    val ordersList = _ordersList

    init {
        getOrdersList()
    }

    fun getOrdersList() {

        val disposable = getOrdersUseCase.getOrdersList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                ordersList.value = it
            }

        disposables.add(disposable)

    }


    override fun onCleared() {
        super.onCleared()
        getOrdersUseCase.dispose()
    }

}