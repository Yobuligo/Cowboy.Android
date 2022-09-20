package com.outlivethesun.cowboyandroid.assets

interface IAssets {
    var balance: Double
    val resources: List<IAsset<*>>
    fun registerOnBalanceChanged(eventHandler: (balance: Double) -> Unit)
}