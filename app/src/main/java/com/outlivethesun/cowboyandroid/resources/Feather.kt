package com.outlivethesun.cowboyandroid.resources

import com.outlivethesun.cowboyandroid.R

object Feather : IResource {
    override val icon: Int get() = R.drawable.feather
    override val name: String get() = "Feathers"
    override val description: String =
        "Feathers for a warm bed, feathers for a nice jacket, feathers to write letters. Buy Geese to get feathers for everybody in the world. We only get feathers for healthy geese which they dropped."
    override val purchasable: Boolean get() = false
    override val neededLand: Double get() = 0.01
    override val minPrice: Float get() = 50f
    override val maxPrice: Float get() = 120f
}