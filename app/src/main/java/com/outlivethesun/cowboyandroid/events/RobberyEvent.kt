package com.outlivethesun.cowboyandroid.events

import com.outlivethesun.cowboyandroid.formatter.toMoney
import com.outlivethesun.cowboyandroid.probability.probability
import com.outlivethesun.cowboyandroid.round.IRound

class RobberyEvent : IEvent {
    override val probability: Float get() = 5f
    override val title: String get() = "Theft"

    override fun isPreConditionFulfilled(round: IRound): Boolean {
        return round.assets.balance > 0
    }

    override fun occurs(round: IRound): String {
        val robbedPercentOfAll = probability()
            .level(500, 90, 100)
            .level(100, 80, 90)
            .level(50, 50, 70)
            .level(10, 20, 50)
            .calculate(1, 20).toInt()
        var robbedBalance = (round.assets.balance / 100 * robbedPercentOfAll).toLong()
        if (robbedBalance == 0L) {
            robbedBalance = 1
        }
        return "FUCK! Call the police! You got robbed. The robbers took ${robbedBalance.toMoney()}."
    }
}