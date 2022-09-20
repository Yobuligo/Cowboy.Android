package com.outlivethesun.cowboyandroid.events

import com.outlivethesun.cowboyandroid.assets.IAssets

class NewbornEvent(override val probability: Float) : IEvent {

    override val title: String
        get() = TODO("Not yet implemented")

    override fun occurs(assets: IAssets): String {
        TODO("Not yet implemented")
    }
}