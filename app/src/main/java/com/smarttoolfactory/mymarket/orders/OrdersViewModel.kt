package com.smarttoolfactory.mymarket.orders

import androidx.lifecycle.ViewModel
import javax.inject.Inject


/**
 * Constructor with @Inject is required to have constructor injection for this ViewModel
 *
 * "ViewModel cannot be provided without an @Inject constructor or an @Provides-annotated method" exception occurs otherwise.
 */
class OrdersViewModel  @Inject constructor() : ViewModel() {

}