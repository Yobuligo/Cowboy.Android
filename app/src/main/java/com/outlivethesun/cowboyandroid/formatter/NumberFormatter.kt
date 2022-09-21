package com.outlivethesun.cowboyandroid.formatter

import com.outlivethesun.cowboyandroid.unit.UNIT_CURRENCY

object NumberFormatter : INumberFormatter {
    override fun toMoney(amount: Double): String {
        return "${toAmount(amount)} $UNIT_CURRENCY"
    }

    override fun toMoney(amount: Long): String {
        return "${toAmount(amount)} $UNIT_CURRENCY"
    }

    override fun toMoney(amount: Int): String {
        return "${toAmount(amount)} $UNIT_CURRENCY"
    }

    override fun toAmount(amount: Double): String {
        val result = convert(amount)
        return result.ifBlank { amount.toString() }
    }

    override fun toAmount(amount: Long): String {
        val result = convert(amount.toDouble())
        return result.ifBlank { amount.toString() }
    }

    override fun toAmount(amount: Int): String {
        val result = convert(amount.toDouble())
        return result.ifBlank { amount.toString() }
    }

    private fun convert(amount: Double): String {
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
            return ""
        }
    }
}