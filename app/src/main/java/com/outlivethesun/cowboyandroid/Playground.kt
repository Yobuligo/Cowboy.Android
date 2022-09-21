package com.outlivethesun.cowboyandroid

import com.outlivethesun.cowboyandroid.probability.probability
import com.outlivethesun.cowboyandroid.probability.randomizer.randomizer


fun determineLotteryAmount(): Int {
    // 100000 .. 1000000 €
    if (randomizer.nextInt(1, 500) == 1) {
        return randomizer.nextInt(100000, 1000000)
    }

    // 10000 .. 100000 €
    if (randomizer.nextInt(1, 100) == 1) {
        return randomizer.nextInt(10000, 100000)
    }

    // 1000 .. 10000 €
    if (randomizer.nextInt(1, 50) == 1) {
        return randomizer.nextInt(1000, 10000)
    }

    // 100 .. 1000 €
    if (randomizer.nextInt(1, 10) == 1) {
        return randomizer.nextInt(100, 1000)
    }

    return randomizer.nextInt(1, 100)
}

fun main() {
    repeat(10000) {
        println(
            probability().level(500, 100000, 1000000).level(100, 10000, 100000)
                .level(50, 1000, 10000).level(10, 100, 1000).calculate(1, 100)
        )
    }
}