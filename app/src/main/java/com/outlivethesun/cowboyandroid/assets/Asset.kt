package com.outlivethesun.cowboyandroid.assets

import com.outlivethesun.cowboyandroid.resources.IResource

class Asset<T : IResource>(override val resource: T, amount: Long) : IAsset<T> {
    private var _amount: Long = 0
    private val eventHandlersOnAmountChanged: MutableList<(amount: Long) -> Unit> = mutableListOf()

    override var amount: Long
        get() = _amount
        set(value) {
            _amount = value
            eventHandlersOnAmountChanged.forEach { eventHandler ->
                eventHandler.invoke(_amount)
            }
        }

    init {
        this.amount = amount
    }

    override val neededLand: Double get() = resource.neededLand * amount

    override fun registerOnAmountChanged(eventHandler: (amount: Long) -> Unit) {
        eventHandlersOnAmountChanged.add(eventHandler)
    }
}