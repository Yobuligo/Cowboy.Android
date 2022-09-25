package com.outlivethesun.cowboyandroid.probability.randomizer

import kotlin.random.Random

val randomizer: IRandomizer by lazy { Randomizer() }

internal class Randomizer : IRandomizer {
    private val random by lazy { Random(System.currentTimeMillis()) }

    override fun nextLong(min: Long, max: Long): Long {
        return random.nextLong(min, max + 1)
    }

    override fun nextInt(min: Int, max: Int): Int {
        return random.nextInt(min, max + 1)
    }

    override fun nextDouble(min: Double, max: Double): Double {
        return random.nextDouble(min, max + 1)
    }

    override fun nextBoolean(): Boolean {
        return random.nextBoolean()
    }
}

