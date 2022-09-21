package com.outlivethesun.cowboyandroid.dialogs.amountInputDialog

import kotlin.math.roundToLong

abstract class AmountConverter : IAmountConverter {
    override fun convertAmountToPercent(amount: Long): Float {
        return amount.div(maxValue.toFloat()).times(100)
    }

    override fun convertPercentToAmount(value: Float): Long {
        return if (value == 1f) {
            maxValue
        } else {
            (maxValue * value).roundToLong()
        }
    }
}