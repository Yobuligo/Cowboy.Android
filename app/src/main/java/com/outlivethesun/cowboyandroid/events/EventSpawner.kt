package com.outlivethesun.cowboyandroid.events

import com.outlivethesun.cowboyandroid.assets.IAssets
import com.outlivethesun.cowboyandroid.randomizer.Randomizer

object EventSpawner : IEventSpawner {
    private val events: List<IEvent> = listOf(
        EggsEvent()
    )

    override fun spawn(assets: IAssets) {
        events.forEach { event ->
            val percent = Randomizer.nextInt(0, 100)
            if (percent <= event.probability) {
                val message = event.occurs(assets)

            }
        }
    }
}