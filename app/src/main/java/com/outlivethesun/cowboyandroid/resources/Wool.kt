package com.outlivethesun.cowboyandroid.resources

import com.outlivethesun.cowboyandroid.R

object Wool : IResource {
    override val icon: Int get() = R.drawable.wool
    override val name: String get() = "Wool"
    override val description: String =
        "You get wool from sheep. So buy sheep and you will get wool for a nice blanket."
    override val purchasable: Boolean get() = false
    override val neededLand: Double get() = 0.0
    override val minPrice: Float get() = 2f
    override val maxPrice: Float get() = 5f
}