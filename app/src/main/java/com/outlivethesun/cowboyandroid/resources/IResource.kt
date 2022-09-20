package com.outlivethesun.cowboyandroid.resources

interface IResource {
    val icon: Int
    val name: String
    val description: String

    /**
     * Provides if the resource can be bought? If not it is a result of another resource (e.g. eggs come from chickens)
     */
    val purchasable: Boolean

    /**
     * Provides the needed land which is required by one piece of the resource
     */
    val neededLand: Double
    val minPrice: Float
    val maxPrice: Float
}
