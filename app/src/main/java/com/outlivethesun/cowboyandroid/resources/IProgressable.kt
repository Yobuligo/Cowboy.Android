package com.outlivethesun.cowboyandroid.resources

import com.outlivethesun.cowboyandroid.round.IRound

/**
 * An implementation of this interface provides a progress
 */
interface IProgressable {
    fun calculateProgress(round: IRound): Int
}