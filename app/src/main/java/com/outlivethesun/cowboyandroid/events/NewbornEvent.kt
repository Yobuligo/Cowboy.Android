package com.outlivethesun.cowboyandroid.events

import com.outlivethesun.cowboyandroid.formatter.toAmount
import com.outlivethesun.cowboyandroid.probability.randomizer.randomizer
import com.outlivethesun.cowboyandroid.resources.IResource
import com.outlivethesun.cowboyandroid.round.IRound

class NewbornEvent(
    private val resource: IResource,
    private val minPercentOfAllGetNewborns: Int,
    private val maxPercentOfAllGetNewborns: Int,
    override val probability: Float
) : IEvent {
    override val title: String get() = "Newborn"

    override fun isPreConditionFulfilled(round: IRound): Boolean {
        val asset = round.findAssetByResourceType(resource::class)
        return asset != null && asset.amount > 1
    }

    /**
     * [minPercentOfAllGetNewborns] - [maxPercentOfAllGetNewborns] of your [resource] gets newborns
     */
    override fun occurs(round: IRound): String {
        val asset = round.findAssetByResourceType(resource::class)
            ?: throw RuntimeException("Asset ${resource::class} must be available.")

        val percent = randomizer.nextInt(minPercentOfAllGetNewborns, maxPercentOfAllGetNewborns)
        var gainedNewborns = (asset.amount.toDouble() / 100 * percent).toLong()
        if (gainedNewborns == 0L) {
            gainedNewborns = 1
        }
        asset.amount += gainedNewborns
        return if (randomizer.nextBoolean()) {
            "Your ${resource.name} live a freestyle life. You gained ${gainedNewborns.toAmount()} newborns."
        } else {
            "You gained ${gainedNewborns.toAmount()} ${resource.name}."
        }
    }
}