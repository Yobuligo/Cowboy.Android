package com.outlivethesun.cowboyandroid.events

import com.outlivethesun.cowboyandroid.assets.IAssets

interface IEventSpawner {
    fun spawn(assets: IAssets)
}