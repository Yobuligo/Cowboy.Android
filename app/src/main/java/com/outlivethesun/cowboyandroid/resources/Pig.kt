package com.outlivethesun.cowboyandroid.resources

import com.outlivethesun.cowboyandroid.R

object Pig : IResource {
    override val icon: Int get() = R.drawable.pig
    override val name: String get() = "Pigs"
    override val description: String =
        "We are thinking about the reasons why holding pigs without killing them to get meat."
    override val purchasable: Boolean get() = true
    override val neededLand: Double get() = 10.0
    override val minPrice: Float get() = 50f
    override val maxPrice: Float get() = 150f
}