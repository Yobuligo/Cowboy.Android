package com.outlivethesun.cowboyandroid.events

import com.outlivethesun.cowboyandroid.round.IRound

interface IEvent {
    val probability: Float
    val title: String
    fun isPreConditionFulfilled(round: IRound): Boolean
    fun occurs(round: IRound): String
}