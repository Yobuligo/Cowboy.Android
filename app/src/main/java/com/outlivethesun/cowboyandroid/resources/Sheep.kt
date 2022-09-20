package com.outlivethesun.cowboyandroid.resources

import com.outlivethesun.cowboyandroid.R

object Sheep : IResource {
    override val icon: Int get() = R.drawable.sheep
    override val name: String get() = "Sheep"
    override val description: String =
        "Sheep are fluffy. Buy them to get wool for pullovers. Its pretty cold in Europe without gas. Buy sheep!"
    override val purchasable: Boolean get() = true
    override val neededLand: Double get() = 10.0
    override val minPrice: Float get() = 25f
    override val maxPrice: Float get() = 100f
}