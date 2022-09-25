package com.outlivethesun.cowboyandroid.round

import com.outlivethesun.cowboyandroid.assets.IAsset
import com.outlivethesun.cowboyandroid.assets.IAssets
import com.outlivethesun.cowboyandroid.resources.IResource
import kotlin.reflect.KClass

class Round(override val name: String, override val assets: IAssets) : IRound {
    private val assetsHash: HashMap<KClass<out IResource>, IAsset<*>> = hashMapOf()

    init {
        assets.resources.forEach { asset ->
            assetsHash[asset.resource::class] = asset
        }
    }

    override fun <T : IResource> findAssetByResourceType(resourceType: KClass<out T>): IAsset<T>? {
        return assetsHash[resourceType] as IAsset<T>
    }
}