package com.outlivethesun.cowboyandroid.resources

import com.outlivethesun.cowboyandroid.R
import com.outlivethesun.cowboyandroid.round.IRound
import kotlin.math.truncate

object Land : IResource, IProgressable {
    override val icon: Int get() = R.drawable.land
    override val name: String get() = "Land"
    override val description: String =
        "Everything you need is land. Land is required to hold animals. No land, no animals. You cannot sell all of your land if you have animals. Animals feel much better in a wide range land. Animals are less sick on a huge land. The progress bar indicates how much land is used by your animals and property. If you buy more animals than you have available space for them they get sick and unhappy."
    override val purchasable: Boolean get() = true
    override val neededLand: Double get() = 0.0
    override val minPrice: Float get() = 50f
    override val maxPrice: Float get() = 1500f

    override fun calculateProgress(round: IRound): Int {
        val assetLand = round.findAssetByResourceType(Land::class)
            ?: throw RuntimeException("Asset ${Land::class} must be available")
        val neededLand = calculateAmountNeededLand(round)
        return ((neededLand / assetLand.amount) * 100).toInt()
    }

    fun calculateAmountNeededLand(round: IRound): Double {
        var neededLand: Double = 0.0
        round.assets.resources.forEach { asset ->
            if (asset.resource !is Land) {
                neededLand += asset.neededLand
            }
        }
        return neededLand
    }

    fun calculateAmountRemainingLand(round: IRound): Double {
        val neededLand = calculateAmountNeededLand(round)
        val assetLand = round.findAssetByResourceType(Land::class)
            ?: throw RuntimeException("Asset ${Land::class} must be available")
        return assetLand.amount - neededLand
    }

    fun calculateAmountResourceOfRemainingLand(round: IRound, resource: IResource): Long {
        val remainingLand = calculateAmountRemainingLand(round)
        return truncate(remainingLand / resource.neededLand).toLong()
    }
}