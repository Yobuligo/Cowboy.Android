package com.outlivethesun.cowboyandroid.probability.randomizer

interface IRandomizer {
    fun nextLong(min: Long, max: Long): Long
    fun nextInt(min: Int, max: Int): Int
    fun nextDouble(min: Double, max: Double): Double
    fun nextBoolean(): Boolean
}