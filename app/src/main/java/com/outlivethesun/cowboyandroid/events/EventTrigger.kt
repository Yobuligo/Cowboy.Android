package com.outlivethesun.cowboyandroid.events

import androidx.fragment.app.FragmentManager
import com.outlivethesun.cowboyandroid.dialogs.eventInfoDialog.EventInfoDialog
import com.outlivethesun.cowboyandroid.randomizer.Randomizer
import com.outlivethesun.cowboyandroid.resources.*
import com.outlivethesun.cowboyandroid.round.IRound

object EventTrigger : IEventTrigger {
    private val events: List<IEvent> = listOf(
        EggsEvent(),
        MilkEvent(),
        WoolEvent(),
        NewbornEvent(Horse, 1, 5, 10f),
        NewbornEvent(Cow, 1, 5, 10f),
        NewbornEvent(Goose, 1, 30, 30f),
        NewbornEvent(Pig, 1, 20, 10f),
        NewbornEvent(Sheep, 1, 30, 15f),
        LotteryEvent(),
        RobberyEvent()
    )

    override fun trigger(round: IRound, fragmentManager: FragmentManager) {
        events.forEach { event ->
            val percent = Randomizer.nextInt(0, 100)
            if (percent > 0 && percent <= event.probability && event.isPreConditionFulfilled(round)) {
                val message = event.occurs(round)
                EventInfoDialog(event.title, message).show(
                    fragmentManager, EventInfoDialog::class.toString()
                )
            }
        }
    }
}