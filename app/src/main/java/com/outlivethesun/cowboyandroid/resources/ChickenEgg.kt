package com.outlivethesun.cowboyandroid.resources

import com.outlivethesun.cowboyandroid.R

object ChickenEgg : IResource {
    override val icon: Int get() = R.drawable.egg
    override val name: String get() = "Chicken Eggs"
    override val description: String =
        "Eggs. Nothing to say about eggs. Buy them, eat them, buy more. Buy chicken and you will get eggs."
    override val purchasable: Boolean get() = false
    override val neededLand: Double get() = 0.01
    override val minPrice: Float get() = 3f
    override val maxPrice: Float get() = 9f
}