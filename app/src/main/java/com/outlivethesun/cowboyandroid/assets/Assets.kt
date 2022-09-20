package com.outlivethesun.cowboyandroid.assets

class Assets(balance: Double, override val resources: List<IAsset<*>>) : IAssets {
    private val eventHandlers: MutableList<(balance: Double) -> Unit> = mutableListOf()
    private var _balance: Double = 0.0

    override var balance: Double
        get() = _balance
        set(value) {
            _balance = value
            notifyOnBalanceChanged()
        }

    init {
        this.balance = balance
    }

    override fun registerOnBalanceChanged(eventHandler: (balance: Double) -> Unit) {
        eventHandlers.add(eventHandler)
    }

    private fun notifyOnBalanceChanged() {
        eventHandlers.forEach { eventHandler ->
            eventHandler.invoke(_balance)
        }
    }
}