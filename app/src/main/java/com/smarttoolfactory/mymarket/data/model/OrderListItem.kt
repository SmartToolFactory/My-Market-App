package com.smarttoolfactory.mymarket.data.model

import com.smarttoolfactory.mymarket.constants.STATE_WAITING_CONFIRMATION


/**
 * Order item that wrapper for [Order] list that is create at same date to display orders in a RecyclerView Adapter
 * with option to show or conceal [ProductDetail]s
 */
class OrderListItem(orderList: Order) {


    var date: String? = orderList.date
    var month: String? = orderList.month
    var state: String = STATE_WAITING_CONFIRMATION
    var order: Order? = orderList
    var id = 0

    init {
        month?.apply {
            month = geMonthName(this)
        }
    }

    /**
     * Convert month rank to name
     */
    private fun geMonthName(monthNum: String): String {
        return when (monthNum) {
            "01" -> "Ocak"
            "02" -> "Şubat"
            "03" -> "Mart"
            "04" -> "Nisan"
            "05" -> "Mayıs"
            "06" -> "Haziran"
            "07" -> "Temmuz"
            "08" -> "Ağustos"
            "09" -> "Eylül"
            "10" -> "Ekim"
            "11" -> "Kasım"
            "12" -> "Aralık"
            else -> "Ocak"
        }

    }


}