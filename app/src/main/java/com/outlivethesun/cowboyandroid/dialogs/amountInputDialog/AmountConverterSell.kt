package com.outlivethesun.cowboyandroid.dialogs.amountInputDialog

import com.outlivethesun.cowboyandroid.assets.IAsset

class AmountConverterSell(private val asset: IAsset<*>) : AmountConverter(), IAmountConverter {
    override val minValue: Long get() = 0
    override val maxValue: Long get() = asset.amount

    override fun convertLandToAmount(): Long {
        return 0
    }
}