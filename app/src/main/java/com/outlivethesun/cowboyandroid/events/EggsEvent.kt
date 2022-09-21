package com.outlivethesun.cowboyandroid.events

import com.outlivethesun.cowboyandroid.formatter.toAmount
import com.outlivethesun.cowboyandroid.probability.randomizer.randomizer
import com.outlivethesun.cowboyandroid.resources.Chicken
import com.outlivethesun.cowboyandroid.resources.ChickenEgg
import com.outlivethesun.cowboyandroid.round.IRound

class EggsEvent : IEvent {
    override val probability: Float get() = 50f

    override val title: String get() = "New eggs"

    override fun isPreConditionFulfilled(round: IRound): Boolean {
        val asset = round.findAssetByResourceType(Chicken::class)
        return asset != null && asset.amount > 1
    }

    override fun occurs(round: IRound): String {
        val assetChicken = round.findAssetByResourceType(Chicken::class)
            ?: throw RuntimeException("Asset ${Chicken::class} must be available.")
        val assetChickenEggs = round.findAssetByResourceType(ChickenEgg::class)
            ?: throw RuntimeException("Asset ${ChickenEgg::class} must be available.")


        // 1 - 40% of chicken get eggs
        val percent = randomizer.nextInt(1, 40)

        var gainedChickenEggs = (assetChicken.amount.toDouble() / 100 * percent).toLong()
        if (gainedChickenEggs == 0L) {
            gainedChickenEggs = 1
        }
        assetChickenEggs.amount += gainedChickenEggs
        return "You did something right. You gained ${gainedChickenEggs.toAmount()} eggs from your chickens."
    }
}