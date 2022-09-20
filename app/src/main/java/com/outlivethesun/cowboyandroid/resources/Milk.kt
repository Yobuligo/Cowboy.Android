package com.outlivethesun.cowboyandroid.resources

import com.outlivethesun.cowboyandroid.R

object Milk : IResource {
    override val icon: Int get() = R.drawable.milk
    override val name: String get() = "Milk"
    override val description: String =
        "Milk the white gold. You get milk from cows. Buy cows to get milk."
    override val purchasable: Boolean get() = false
    override val neededLand: Double get() = 0.01
    override val minPrice: Float get() = 1f
    override val maxPrice: Float get() = 3f
}