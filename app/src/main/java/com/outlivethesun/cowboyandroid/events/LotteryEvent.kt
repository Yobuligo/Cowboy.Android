package com.outlivethesun.cowboyandroid.events

import com.outlivethesun.cowboyandroid.formatter.toMoney
import com.outlivethesun.cowboyandroid.probability.probability
import com.outlivethesun.cowboyandroid.round.IRound

class LotteryEvent : IEvent {
    override val probability: Float get() = 10f

    override val title: String
        get() = "Lottery"

    override fun isPreConditionFulfilled(round: IRound): Boolean {
        return true
    }

    override fun occurs(round: IRound): String {
        val lotteryAmount = probability()
            .level(500, 100000, 1000000)
            .level(100, 10000, 100000)
            .level(50, 1000, 10000)
            .level(10, 100, 1000)
            .calculate(1, 100).toDouble()
        round.assets.balance += lotteryAmount
        return "WOW! You won the lottery and gained ${lotteryAmount.toMoney()}."
    }
}