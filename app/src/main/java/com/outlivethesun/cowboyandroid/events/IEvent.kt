package com.outlivethesun.cowboyandroid.events

import com.outlivethesun.cowboyandroid.assets.IAssets

interface IEvent {
    val probability: Float
    val title: String
    fun occurs(assets: IAssets): String
}