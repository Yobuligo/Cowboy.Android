package com.outlivethesun.cowboyandroid.events

import com.outlivethesun.cowboyandroid.randomizer.Randomizer
import com.outlivethesun.cowboyandroid.resources.Cow
import com.outlivethesun.cowboyandroid.resources.Milk
import com.outlivethesun.cowboyandroid.round.IRound

class MilkEvent : IEvent {
    override val probability: Float get() = 50f
    override val title: String get() = "New milk"

    override fun isPreConditionFulfilled(round: IRound): Boolean {
        val asset = round.findAssetByResourceType(Cow::class)
        return asset != null && asset.amount > 0
    }

    override fun occurs(round: IRound): String {
        val assetCow = round.findAssetByResourceType(Cow::class)
            ?: throw RuntimeException("Asset ${Cow::class} must be available.")
        val assetMilk = round.findAssetByResourceType(Milk::class)
            ?: throw RuntimeException("Asset ${Milk::class} must be available.")


        // 0 - 80% of cows get milk
        val percent = Randomizer.nextInt(1, 80)
        var gainedMilk = (assetCow.amount / 100 * percent)
        if (gainedMilk == 0L) {
            gainedMilk = 1
        }
        assetMilk.amount += gainedMilk
        return "Your ${assetCow.resource.name} are healthy. You gained $gainedMilk milk."
    }
}