package com.outlivethesun.cowboyandroid.events

import com.outlivethesun.cowboyandroid.formatter.NumberFormatter
import com.outlivethesun.cowboyandroid.randomizer.randomizer
import com.outlivethesun.cowboyandroid.round.IRound

class RobberyEvent : IEvent {
    override val probability: Float get() = 5f
    override val title: String get() = "Theft"

    override fun isPreConditionFulfilled(round: IRound): Boolean {
        return round.assets.balance > 0
    }

    override fun occurs(round: IRound): String {
        val robbedPercentOfAll = determineRobbedPercentOfAll()
        var robbedBalance = (round.assets.balance / 100 * robbedPercentOfAll).toLong()
        if (robbedBalance == 0L) {
            robbedBalance = 1
        }
        return "FUCK! Call the police! You got robbed. The robbers took ${
            NumberFormatter.toMoney(
                robbedBalance
            )
        }."
    }

    private fun determineRobbedPercentOfAll(): Int {
        // 90 .. 100 % of your money
        if (randomizer.nextInt(1, 500) == 1) {
            return randomizer.nextInt(90, 100)
        }

        // 80 .. 90
        if (randomizer.nextInt(1, 100) == 1) {
            return randomizer.nextInt(80, 90)
        }

        // 50 .. 70
        if (randomizer.nextInt(1, 50) == 1) {
            return randomizer.nextInt(50, 70)
        }

        // 20 .. 50
        if (randomizer.nextInt(1, 10) == 1) {
            return randomizer.nextInt(20, 50)
        }

        return randomizer.nextInt(1, 20)
    }

}