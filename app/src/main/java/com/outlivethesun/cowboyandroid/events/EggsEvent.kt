package com.outlivethesun.cowboyandroid.events

import com.outlivethesun.cowboyandroid.randomizer.Randomizer
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


        // 0 - 40% of chicken get eggs
        val percent = Randomizer.nextInt(1, 40)
        var gainedChickenEggs = (assetChicken.amount / 100 * percent)
        if (gainedChickenEggs == 0L){
            gainedChickenEggs = 1
        }
        assetChickenEggs.amount += gainedChickenEggs
        return "You did something right. You gained $gainedChickenEggs eggs from your chickens."
    }
}