package com.outlivethesun.cowboyandroid.resources

import com.outlivethesun.cowboyandroid.R

object Goose : IResource {
    override val icon: Int get() = R.drawable.goose
    override val name: String get() = "Geese"
    override val description: String =
        "Well, there was space for another flying animal. Why not take geese?"
    override val purchasable: Boolean get() = true
    override val neededLand: Double get() = 2.0
    override val minPrice: Float get() = 20f
    override val maxPrice: Float get() = 30f
}