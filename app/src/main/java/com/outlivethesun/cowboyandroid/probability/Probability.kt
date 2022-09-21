package com.outlivethesun.cowboyandroid.probability

import com.outlivethesun.cowboyandroid.probability.randomizer.randomizer

typealias ProbabilityLevel<T> = Triple<Int, T, T>
typealias ProbabilityLevelMutableList<T> = MutableList<ProbabilityLevel<T>>

fun probability(): IProbability {
    return Probability()
}

fun Double.percent(percent: Int): Double {
    return this / 100 * percent
}

fun Double.percent(minPercent: Int, maxPercent: Int): Double {
    return this / 100 * randomizer.nextInt(minPercent, maxPercent)
}

fun Long.percent(percent: Int): Long {
    return (this.toDouble() / 100 * percent).toLong()
}

fun Long.percent(minPercent: Int, maxPercent: Int): Long {
    return (this.toDouble() / 100 * randomizer.nextInt(minPercent, maxPercent)).toLong()
}

fun Int.percent(percent: Int): Int {
    return (this.toDouble() / 100 * percent).toInt()
}

fun Int.percent(minPercent: Int, maxPercent: Int): Int {
    return (this.toDouble() / 100 * randomizer.nextInt(minPercent, maxPercent)).toInt()
}

class Probability : IProbability {
    private val probabilityLevels: ProbabilityLevelMutableList<Long> = mutableListOf()

    override fun level(probability: Int, min: Long, max: Long): IProbability {
        probabilityLevels.add(Triple(probability, min, max))
        return this
    }

    override fun calculate(defaultMin: Long, defaultMax: Long): Long {
        probabilityLevels.sortByDescending { it.first }
        probabilityLevels.forEach { probabilityLevel ->
            if (randomizer.nextInt(1, probabilityLevel.first) == 1) {
                return randomizer.nextLong(probabilityLevel.second, probabilityLevel.third)
            }
        }
        return randomizer.nextLong(defaultMin, defaultMax)
    }
}