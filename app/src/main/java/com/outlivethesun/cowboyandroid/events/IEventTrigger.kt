package com.outlivethesun.cowboyandroid.events

import androidx.fragment.app.FragmentManager
import com.outlivethesun.cowboyandroid.round.IRound

interface IEventTrigger {
    fun trigger(round: IRound, fragmentManager: FragmentManager)
}