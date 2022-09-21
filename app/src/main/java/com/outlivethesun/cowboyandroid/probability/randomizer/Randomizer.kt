package com.outlivethesun.cowboyandroid.probability.randomizer

import kotlin.random.Random

val randomizer by lazy { Randomizer() }

class Randomizer {
    private val random by lazy { Random(System.currentTimeMillis()) }

    fun nextLong(min: Long, max: Long): Long {
        return random.nextLong(min, max + 1)
    }

    fun nextInt(min: Int, max: Int): Int {
        return random.nextInt(min, max + 1)
    }

    fun nextDouble(min: Double, max: Double): Double {
        return random.nextDouble(min, max + 1)
    }

    fun nextBoolean(): Boolean {
        return random.nextBoolean()
    }
}

