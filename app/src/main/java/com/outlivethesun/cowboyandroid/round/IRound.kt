package com.outlivethesun.cowboyandroid.round

import com.outlivethesun.cowboyandroid.assets.IAsset
import com.outlivethesun.cowboyandroid.assets.IAssets
import com.outlivethesun.cowboyandroid.resources.IResource
import kotlin.reflect.KClass

interface IRound {
    val name: String
    val assets: IAssets
    fun <T : IResource> findAssetByResourceType(resourceType: KClass<out T>): IAsset<T>?
}