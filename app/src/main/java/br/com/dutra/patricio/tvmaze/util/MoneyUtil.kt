package br.com.dutra.patricio.tvmaze.util

import java.text.NumberFormat

class MoneyUtil {

    companion object {

        val moneyLocal = NumberFormat.getCurrencyInstance()

        @JvmStatic
        fun moneyFormat(value:Double): String{
            return moneyLocal.format(value)
        }

    }

}