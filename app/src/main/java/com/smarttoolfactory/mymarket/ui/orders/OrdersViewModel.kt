package com.smarttoolfactory.mymarket.ui.orders

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.smarttoolfactory.mymarket.data.model.OrderListItem
import com.smarttoolfactory.mymarket.domain.GetOrderListUseCase
import com.smarttoolfactory.mymarket.utils.SingleLiveEvent
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

    private val _orderList = MutableLiveData<List<OrderListItem>>()


    /**
     * Live data that contains order and does not expose _orderList to UI.
     * This liveData is also used for data-binding with list via
     * app:items= of RecyclerViewBinding
     *
     */
    val items: LiveData<List<OrderListItem>> = _orderList

    /**
     * Position of item touched by user to expand or shrink
     */
    val expandPosition: SingleLiveEvent<Int> = SingleLiveEvent()

    /**
     * Loading indicator to show used fetch progress is going on
     */
    val loading = SingleLiveEvent<Boolean>()


    init {

        loading.value = false

        getOrderList()
    }

    private fun getOrderList() {

        loading.postValue(true)

        val disposable = getOrdersUseCase.getOrderItemList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

            .doOnError {
                loading.postValue(false)
            }
            .doOnNext {
                loading.postValue(false)
            }
            .subscribe({

                it?.let {
                    _orderList.value = it
                }


            },{
                println("OrdersViewModel getOrderList() onError() ${it.message}")
            })

        disposables.add(disposable)

    }

    /**
     * Show or conceal product details
     */
    fun showProductDetails(orderListItem: OrderListItem) {

        orderListItem.isExpanded = (!orderListItem.isExpanded)

        expandPosition.value = orderListItem.id
    }


    override fun onCleared() {
        super.onCleared()
        getOrdersUseCase.dispose()
    }

}