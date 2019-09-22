package com.smarttoolfactory.mymarket.data.model


/**
 * Order item that wrapper for [Order] list that is create at same date to display orders in a RecyclerView Adapter
 * with option to show or conceal [ProductDetail]s
 */
class OrderListItem(orderList: Order) {

    var date: String? = orderList.date
    var month: String? = orderList.month
    var order: Order? = orderList

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