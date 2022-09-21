package com.outlivethesun.cowboyandroid.probability

interface IProbability {
    fun level(probability: Int, min: Long, max: Long): IProbability
    fun calculate(defaultMin: Long, defaultMax: Long): Long
}