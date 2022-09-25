package com.outlivethesun.cowboyandroid.events

import android.util.Log
import androidx.fragment.app.FragmentManager
import com.outlivethesun.cowboyandroid.dialogs.eventInfoDialog.EventInfoDialog
import com.outlivethesun.cowboyandroid.log.LOG_PREFIX
import com.outlivethesun.cowboyandroid.probability.randomizer.randomizer
import com.outlivethesun.cowboyandroid.resources.*
import com.outlivethesun.cowboyandroid.round.IRound

object EventTrigger : IEventTrigger {
    private val events: List<IEvent> = listOf(
        EggsEvent(),
        MilkEvent(),
        WoolEvent(),
        FeathersEvent(),
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
            val percent = randomizer.nextInt(0, 100)
            Log.i("$LOG_PREFIX EventTrigger", "Event '${event.title}'. It has a probability of '${event.probability}%'. Calculated probability is '$percent'.")
            if (percent > 0 && percent <= event.probability && event.isPreConditionFulfilled(round)) {
                val message = event.occurs(round)
                EventInfoDialog(event.title, message).show(
                    fragmentManager, EventInfoDialog::class.toString()
                )
            }
        }
    }
}