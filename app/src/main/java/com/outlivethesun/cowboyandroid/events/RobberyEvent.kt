package com.outlivethesun.cowboyandroid.events

import com.outlivethesun.cowboyandroid.randomizer.Randomizer
import com.outlivethesun.cowboyandroid.round.IRound
import com.outlivethesun.cowboyandroid.unit.UNIT_CURRENCY

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
            robbedBalance++
        }
        return "FUCK! Call the police! You got robbed. The robbers took $robbedBalance $UNIT_CURRENCY."
    }

    private fun determineRobbedPercentOfAll(): Int {
        // 90 .. 100 % of your money
        if (Randomizer.nextInt(1, 500) == 1) {
            return Randomizer.nextInt(90, 100)
        }

        // 80 .. 90
        if (Randomizer.nextInt(1, 100) == 1) {
            return Randomizer.nextInt(80, 90)
        }

        // 50 .. 70
        if (Randomizer.nextInt(1, 50) == 1) {
            return Randomizer.nextInt(50, 70)
        }

        // 20 .. 50
        if (Randomizer.nextInt(1, 10) == 1) {
            return Randomizer.nextInt(20, 50)
        }

        return Randomizer.nextInt(1, 20)
    }

}