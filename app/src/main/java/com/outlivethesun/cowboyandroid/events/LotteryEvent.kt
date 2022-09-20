package com.outlivethesun.cowboyandroid.events

import com.outlivethesun.cowboyandroid.randomizer.Randomizer
import com.outlivethesun.cowboyandroid.round.IRound
import com.outlivethesun.cowboyandroid.unit.UNIT_CURRENCY

class LotteryEvent : IEvent {
    override val probability: Float get() = 10f

    override val title: String
        get() = "Lottery"

    override fun isPreConditionFulfilled(round: IRound): Boolean {
        return true
    }

    override fun occurs(round: IRound): String {
        var lotteryAmount = determineLotteryAmount()
        round.assets.balance += lotteryAmount
        return "WOW! You won the lottery and gained $lotteryAmount $UNIT_CURRENCY."
    }

    private fun determineLotteryAmount(): Int {
        // 100000 .. 1000000 €
        if (Randomizer.nextInt(1, 500) == 1) {
            return Randomizer.nextInt(100000, 1000000)
        }

        // 10000 .. 100000 €
        if (Randomizer.nextInt(1, 100) == 1) {
            return Randomizer.nextInt(10000, 100000)
        }

        // 1000 .. 10000 €
        if (Randomizer.nextInt(1, 50) == 1) {
            return Randomizer.nextInt(1000, 10000)
        }

        // 100 .. 1000 €
        if (Randomizer.nextInt(1, 10) == 1) {
            return Randomizer.nextInt(100, 1000)
        }

        return Randomizer.nextInt(1, 100)
    }
}