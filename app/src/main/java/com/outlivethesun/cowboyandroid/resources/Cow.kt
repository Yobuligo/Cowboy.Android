package com.outlivethesun.cowboyandroid.resources

import com.outlivethesun.cowboyandroid.R

object Cow : IResource {
    override val icon: Int get() = R.drawable.cow
    override val name: String get() = "Cows"
    override val description: String =
        "Cows are loving grass. Let them eat it and you will get lovely tasting milk for your coffee for you cacao or just natural."
    override val purchasable: Boolean get() = true
    override val neededLand: Double get() = 20.0
    override val minPrice: Float get() = 150f
    override val maxPrice: Float get() = 350f
}