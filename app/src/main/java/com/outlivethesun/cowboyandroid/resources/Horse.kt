package com.outlivethesun.cowboyandroid.resources

import com.outlivethesun.cowboyandroid.R

object Horse : IResource {
    override val icon: Int get() = R.drawable.horse
    override val name: String get() = "Horses"
    override val description: String =
        "Horses are not only noble and sometimes conceited. They are perfect for riding and working! Buy horses for doing work."
    override val purchasable: Boolean get() = true
    override val neededLand: Double get() = 50.0
    override val minPrice: Float get() = 350f
    override val maxPrice: Float get() = 750f
}