package com.outlivethesun.cowboyandroid.events

import com.outlivethesun.cowboyandroid.formatter.NumberFormatter
import com.outlivethesun.cowboyandroid.randomizer.Randomizer
import com.outlivethesun.cowboyandroid.resources.Sheep
import com.outlivethesun.cowboyandroid.resources.Wool
import com.outlivethesun.cowboyandroid.round.IRound

class WoolEvent : IEvent {
    override val probability: Float get() = 20f
    override val title: String get() = "New wool"

    override fun isPreConditionFulfilled(round: IRound): Boolean {
        val asset = round.findAssetByResourceType(Sheep::class)
        return asset != null && asset.amount > 0
    }

    override fun occurs(round: IRound): String {
        val assetSheep = round.findAssetByResourceType(Sheep::class)
            ?: throw RuntimeException("Asset ${Sheep::class} must be available.")
        val assetWool = round.findAssetByResourceType(Wool::class)
            ?: throw RuntimeException("Asset ${Wool::class} must be available.")


        // 0 - 90% of sheep get wool
        val percent = Randomizer.nextInt(1, 90)
        var gainedWool = (assetSheep.amount.toDouble() / 100 * percent).toLong()
        if (gainedWool == 0L) {
            gainedWool = 1
        }
        assetWool.amount += gainedWool
        return "Your ${assetSheep.resource.name} brought you ${NumberFormatter.toAmount(gainedWool)} wool."
    }
}