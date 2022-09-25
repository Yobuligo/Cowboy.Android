package com.outlivethesun.cowboyandroid.events

import com.outlivethesun.cowboyandroid.formatter.toAmount
import com.outlivethesun.cowboyandroid.probability.percentRange
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
        var gainedWool = assetSheep.amount.percentRange(1, 90)
        assetWool.amount += gainedWool
        return "Your ${assetSheep.resource.name} brought you ${gainedWool.toAmount()} wool."
    }
}