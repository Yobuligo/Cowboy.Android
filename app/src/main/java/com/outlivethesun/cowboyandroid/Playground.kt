package com.outlivethesun.cowboyandroid

import com.outlivethesun.cowboyandroid.assets.AssetsFactory
import com.outlivethesun.cowboyandroid.dialogs.amountInputDialog.AmountConverterBuy
import com.outlivethesun.cowboyandroid.formatter.NumberFormatter
import com.outlivethesun.cowboyandroid.resources.Horse
import com.outlivethesun.cowboyandroid.round.Round

fun main() {
    val round = Round("Peter", AssetsFactory().create())
    val amountConverter = AmountConverterBuy(round, Horse)
    println("Remaining land for ${NumberFormatter.toAmount(10.0)} ${Horse.name}.")
}