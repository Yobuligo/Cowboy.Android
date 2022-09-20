package com.outlivethesun.cowboyandroid.round

interface IMove {
    val move: Long
    fun next()
    fun registerOnFinished(eventHandler: (move: Long) -> Unit)
    fun registerOnNext(eventHandler: (move: Long) -> Unit)
}