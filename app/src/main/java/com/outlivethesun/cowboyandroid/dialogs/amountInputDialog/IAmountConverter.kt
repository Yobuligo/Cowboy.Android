package com.outlivethesun.cowboyandroid.dialogs.amountInputDialog

interface IAmountConverter {
    val minValue: Long
    val maxValue: Long
    fun convertAmountToPercent(amount: Long): Float
    fun convertPercentToAmount(value: Float): Long
    fun convertLandToAmount(): Long
}