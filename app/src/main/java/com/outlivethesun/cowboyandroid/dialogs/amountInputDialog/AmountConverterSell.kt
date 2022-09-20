package com.outlivethesun.cowboyandroid.dialogs.amountInputDialog

import com.outlivethesun.cowboyandroid.assets.IAsset
import kotlin.math.roundToLong

class AmountConverterSell(private val asset: IAsset<*>) : IAmountConverter {
    override val minValue: Long get() = 0
    override val maxValue: Long get() = asset.amount

    override fun convertAmountToPercent(amount: Long): Float {
        return amount.div(maxValue.toFloat()).times(100)
    }

    override fun convertPercentToAmount(value: Float): Long {
        return (maxValue * value).roundToLong()
    }

    override fun convertLandToAmount(): Long {
        return 0
    }
}