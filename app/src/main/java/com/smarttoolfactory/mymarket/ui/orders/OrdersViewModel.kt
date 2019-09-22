package com.smarttoolfactory.mymarket.ui.orders

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.smarttoolfactory.mymarket.data.model.Order
import com.smarttoolfactory.mymarket.domain.GetOrderListUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


/**
 * Constructor with @Inject is required to have constructor injection for this ViewModel
 *
 * "ViewModel cannot be provided without an @Inject constructor or an @Provides-annotated method" exception occurs otherwise.
 */
class OrdersViewModel @Inject constructor(private val getOrdersUseCase: GetOrderListUseCase) :
    ViewModel() {


    private val disposables = CompositeDisposable()

    private val _orderList = MutableLiveData<List<Order>>()

    /**
     * Live data that contains order and does not expose _orderList to UI.
     * This liveData is also used for data-binding with list via
     * app:items= of RecyclerViewBinding
     *
     */
    val items = _orderList

    init {
        getOrderList()
    }

    private fun getOrderList() {

        val disposable = getOrdersUseCase.getOrderList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                items.value = it
            }

        disposables.add(disposable)

    }


    override fun onCleared() {
        super.onCleared()
        getOrdersUseCase.dispose()
    }

}