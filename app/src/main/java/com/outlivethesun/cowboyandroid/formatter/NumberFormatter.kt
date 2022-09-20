package com.outlivethesun.cowboyandroid.formatter

import com.outlivethesun.cowboyandroid.unit.UNIT_CURRENCY

object NumberFormatter : INumberFormatter {
    override fun toMoney(amount: Double): String {
        return "${toAmount(amount)} $UNIT_CURRENCY"
    }

    override fun toMoney(amount: Long): String {
        return toMoney(amount.toDouble())
    }

    override fun toMoney(amount: Int): String {
        return toMoney(amount.toDouble())
    }

    override fun toAmount(amount: Double): String {
        return if (amount > 1000000000000) {
            val bio = (amount / 1000000000000)
            "${String.format("%.2f", bio)} Bio"
        } else if (amount > 1000000000) {
            val mrd = (amount / 1000000000)
            "${String.format("%.2f", mrd)} Mrd"
        } else if (amount > 1000000) {
            val mio = (amount / 1000000)
            "${String.format("%.2f", mio)} Mio"
        } else {
            return amount.toString()
        }
    }

    override fun toAmount(amount: Long): String {
        return toAmount(amount.toDouble())
    }
}