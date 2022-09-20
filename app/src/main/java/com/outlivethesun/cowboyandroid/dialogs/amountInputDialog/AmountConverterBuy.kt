package com.outlivethesun.cowboyandroid.dialogs.amountInputDialog

import com.outlivethesun.cowboyandroid.resources.IResource
import com.outlivethesun.cowboyandroid.resources.Land
import com.outlivethesun.cowboyandroid.round.IRound
import com.outlivethesun.cowboyandroid.stockMarket.StockMarket
import kotlin.math.roundToLong
import kotlin.math.truncate

class AmountConverterBuy(private val round: IRound, private val resource: IResource) :
    IAmountConverter {
    override val minValue: Long get() = 0
    override val maxValue: Long
        get() = truncate(
            round.assets.balance.div(
                StockMarket.getResourcePrice(
                    resource
                )
            )
        ).toLong()

    override fun convertAmountToPercent(amount: Long): Float {
        return amount.div(maxValue.toFloat()).times(100)
    }

    override fun convertPercentToAmount(value: Float): Long {
        return (maxValue * value).roundToLong()
    }

    override fun convertLandToAmount(): Long {
        return round.findAssetByResourceType(Land::class)!!.resource.calculateAmountResourceOfRemainingLand(
            round, resource
        )
    }
}