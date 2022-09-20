package com.outlivethesun.cowboyandroid.round

object Move : IMove {
    private var _move = 0L
    private val eventHandlersOnFinished: MutableList<(move: Long) -> Unit> = mutableListOf()
    private val eventHandlersOnNext: MutableList<(move: Long) -> Unit> = mutableListOf()

    override val move: Long get() = _move

    override fun next() {
        notifyOnFinished()
        _move++
        notifyOnNext()
    }

    override fun registerOnFinished(eventHandler: (move: Long) -> Unit) {
        eventHandlersOnFinished.add(eventHandler)
    }

    override fun registerOnNext(eventHandler: (move: Long) -> Unit) {
        eventHandlersOnNext.add(eventHandler)
    }

    private fun notifyOnNext() {
        eventHandlersOnNext.forEach { eventHandler ->
            eventHandler.invoke(move)
        }
    }

    private fun notifyOnFinished() {
        eventHandlersOnFinished.forEach { eventHandler ->
            eventHandler.invoke(move)
        }
    }
}