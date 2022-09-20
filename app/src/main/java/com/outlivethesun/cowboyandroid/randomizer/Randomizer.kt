package com.outlivethesun.cowboyandroid.randomizer

import kotlin.random.Random

val Randomizer by lazy { Random(System.currentTimeMillis()) }