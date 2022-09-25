package com.outlivethesun.cowboyandroid.probability

import com.outlivethesun.cowboyandroid.probability.randomizer.randomizer
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.roundToInt
import kotlin.math.roundToLong

typealias ProbabilityLevel<T> = Triple<Int, T, T>
typealias ProbabilityLevelMutableList<T> = MutableList<ProbabilityLevel<T>>

fun probability(): IProbability {
    return Probability()
}

fun Double.percent(percent: Int): Double {
    return this / 100 * percent
}

fun Double.percentRange(minPercent: Int, maxPercent: Int): Double {
    // determine min integer value e.g. 60% of 12 is 7.2 and has to be round up to 8 as 7 would be 58,33% and so lower as 60% and out of range
    val minValue = ceil(this / 100 * minPercent)

    // determine max integer value e.g. 80% of 12 is 9,6 and has to be round down to 9 as 10 would be 83,33% and so higher as 80% and out of range
    val maxValue = floor(this / 100 * maxPercent)

    // if minValue is greater than maxValue return minValue e.g. range min 2% and max 8% from 10
    // min value is (2% of 10 = 0.2 -> ceil -> 1)
    // max value is (8% of 10 = 0.8 -> floor -> 0)
    // so take at least the min value instead of returning 0
    if (minValue > maxValue) {
        return minValue
    }

    // value must be greater than minValue
    val value = (this / 100 * randomizer.nextInt(minPercent, maxPercent))
    if (value < minValue) {
        return minValue
    }

    // value must be greater than maxValue
    if (value > maxValue) {
        return maxValue
    }

    return value
}

fun Long.percent(percent: Int): Long {
    return (this.toDouble() / 100 * percent).roundToLong()
}

fun Long.percentRange(minPercent: Int, maxPercent: Int): Long {
    // determine min integer value e.g. 60% of 12 is 7.2 and has to be round up to 8 as 7 would be 58,33% and so lower as 60% and out of range
    val minValue = ceil(toDouble() / 100 * minPercent).toLong()

    // determine max integer value e.g. 80% of 12 is 9,6 and has to be round down to 9 as 10 would be 83,33% and so higher as 80% and out of range
    val maxValue = floor(toDouble() / 100 * maxPercent).toLong()

    // if minValue is greater than maxValue return minValue e.g. range min 2% and max 8% from 10
    // min value is (2% of 10 = 0.2 -> ceil -> 1)
    // max value is (8% of 10 = 0.8 -> floor -> 0)
    // so take at least the min value instead of returning 0
    if (minValue > maxValue) {
        return minValue
    }

    // value must be greater than minValue
    val value = (toDouble() / 100 * randomizer.nextInt(minPercent, maxPercent)).roundToLong()
    if (value < minValue) {
        return minValue
    }

    // value must be greater than maxValue
    if (value > maxValue) {
        return maxValue
    }

    return value
}

fun Int.percent(percent: Int): Int {
    return (this.toDouble() / 100 * percent).roundToInt()
}

fun Int.percentRange(minPercent: Int, maxPercent: Int): Int {
    // determine min integer value e.g. 60% of 12 is 7.2 and has to be round up to 8 as 7 would be 58,33% and so lower as 60% and out of range
    val minValue = ceil(toDouble() / 100 * minPercent).toInt()

    // determine max integer value e.g. 80% of 12 is 9,6 and has to be round down to 9 as 10 would be 83,33% and so higher as 80% and out of range
    val maxValue = floor(toDouble() / 100 * maxPercent).toInt()

    // if minValue is greater than maxValue return minValue e.g. range min 2% and max 8% from 10
    // min value is (2% of 10 = 0.2 -> ceil -> 1)
    // max value is (8% of 10 = 0.8 -> floor -> 0)
    // so take at least the min value instead of returning 0
    if (minValue > maxValue) {
        return minValue
    }

    // value must be greater than minValue
    val value = (toDouble() / 100 * randomizer.nextInt(minPercent, maxPercent)).roundToInt()
    if (value < minValue) {
        return minValue
    }

    // value must be greater than maxValue
    if (value > maxValue) {
        return maxValue
    }

    return value
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