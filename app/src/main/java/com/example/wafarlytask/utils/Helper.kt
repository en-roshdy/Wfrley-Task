package com.example.wafarlytask.utils

import android.content.Context
import com.example.wafarlytask.R
import java.util.Locale

object Helper {

     fun roundToTwoDecimalPlaces(value: Double): Double {
        return String.format(Locale("en"),"%.2f", value).toDouble()
    }


    fun  priceWithCurrency(context: Context, value: Double) : String{
        return "${roundToTwoDecimalPlaces(value)} ${context.getString(R.string.currencyShortCut)}"
    }

}