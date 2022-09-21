package com.outlivethesun.cowboyandroid.probability

import com.outlivethesun.cowboyandroid.probability.randomizer.randomizer

typealias ProbabilityLevel<T> = Triple<Int, T, T>
typealias ProbabilityLevelMutableList<T> = MutableList<ProbabilityLevel<T>>

fun probability(): IProbability {
    return Probability()
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