package com.outlivethesun.cowboyandroid.events

import com.outlivethesun.cowboyandroid.assets.IAsset
import com.outlivethesun.cowboyandroid.assets.IAssets
import com.outlivethesun.cowboyandroid.randomizer.Randomizer
import com.outlivethesun.cowboyandroid.resources.Chicken
import com.outlivethesun.cowboyandroid.resources.ChickenEgg
import com.outlivethesun.cowboyandroid.resources.IResource
import kotlin.reflect.KClass

class EggsEvent : IEvent {
    override val probability: Float
        get() = 50f

    override val title: String get() = "New eggs"

    override fun occurs(assets: IAssets): String {
        val chicken = findAssetByResource(assets, Chicken::class)
        val chickenEggs = findAssetByResource(assets, ChickenEgg::class)

        // 0 - 40% of chicken get eggs
        val percent = Randomizer.nextInt(0, 40)
        val gainedChickenEggs = (chicken.amount / 100 * percent)
        chickenEggs.amount += gainedChickenEggs
        return "You did something right. You gained $gainedChickenEggs eggs from your chickens."
    }

    private fun <T : IResource> findAssetByResource(
        assets: IAssets, resourceKClass: KClass<T>
    ): IAsset<T> {
        assets.resources.forEach { asset ->
            if (asset.resource::class == resourceKClass) {
                return asset as IAsset<T>
            }
        }

        throw RuntimeException()
    }
}