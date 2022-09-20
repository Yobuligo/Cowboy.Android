package com.outlivethesun.cowboyandroid.round

import com.outlivethesun.cowboyandroid.assets.IAsset
import com.outlivethesun.cowboyandroid.assets.IAssets
import com.outlivethesun.cowboyandroid.resources.IResource
import kotlin.reflect.KClass

class Round(override val name: String, override val assets: IAssets) : IRound {
    override fun <T : IResource> findAssetByResourceType(resourceType: KClass<T>): IAsset<T>? {
        assets.resources.forEach { asset ->
            if (asset.resource::class == resourceType) {
                return asset as IAsset<T>
            }
        }
        return null
    }
}