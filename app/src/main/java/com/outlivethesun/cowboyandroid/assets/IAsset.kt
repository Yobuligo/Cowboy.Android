package com.outlivethesun.cowboyandroid.assets

import com.outlivethesun.cowboyandroid.resources.IResource

interface IAsset<T : IResource> {
    val resource: T
    var amount: Long
    val neededLand: Double
    fun registerOnAmountChanged(eventHandler: (amount: Long) -> Unit)
}