package com.outlivethesun.cowboyandroid

import com.outlivethesun.cowboyandroid.randomizer.randomizer
import com.outlivethesun.cowboyandroid.resources.Milk

fun main() {
    val minPrice = Milk.minPrice.toLong()
    val maxPrice = Milk.maxPrice.toLong()
    repeat(100) {
        println(randomizer.nextInt(1, 3))
    }
}