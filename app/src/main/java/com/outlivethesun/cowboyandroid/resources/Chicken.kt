package com.outlivethesun.cowboyandroid.resources

import com.outlivethesun.cowboyandroid.R

object Chicken : IResource {
    override val icon: Int get() = R.drawable.chicken
    override val name: String get() = "Chickens"
    override val description: String
        get() = "Chickens are nice animals, which are partially a little stupid. Anyway chickens lay eggs and we love them for breakfast. So buy chickens to get eggs."
    override val purchasable: Boolean get() = true
    override val neededLand: Double get() = 1.0
    override val minPrice: Float get() = 9f
    override val maxPrice: Float get() = 15f
}