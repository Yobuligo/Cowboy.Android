package com.outlivethesun.cowboyandroid.events

import com.outlivethesun.cowboyandroid.formatter.toAmount
import com.outlivethesun.cowboyandroid.probability.percentRange
import com.outlivethesun.cowboyandroid.resources.Feather
import com.outlivethesun.cowboyandroid.resources.Goose
import com.outlivethesun.cowboyandroid.round.IRound

class FeathersEvent : IEvent {
    override val probability: Float get() = 20f

    override val title: String get() = "New feathers"

    override fun isPreConditionFulfilled(round: IRound): Boolean {
        val asset = round.findAssetByResourceType(Goose::class)
        return asset != null && asset.amount > 1
    }

    override fun occurs(round: IRound): String {
        val assetGoose = round.findAssetByResourceType(Goose::class)
            ?: throw RuntimeException("Asset ${Goose::class} must be available.")
        val assetFeathers = round.findAssetByResourceType(Feather::class) ?: throw RuntimeException(
            "Asset ${Feather::class} must be available."
        )

        // 1 - 10% of geese yield feathers
        var gainedFeathers = assetGoose.amount.percentRange(1, 10)
        assetFeathers.amount += gainedFeathers
        return "Your geese dropped ${gainedFeathers.toAmount()} ${Feather.name} for sale."
    }
}