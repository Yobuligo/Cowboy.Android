package com.outlivethesun.cowboyandroid.formatter

import com.outlivethesun.cowboyandroid.unit.UNIT_CURRENCY

fun Double.convertNumber(): String {
    return if (this > 1000000000000) {
        val bio = (this / 1000000000000)
        "${String.format("%.2f", bio)} Bio"
    } else if (this > 1000000000) {
        val mrd = (this / 1000000000)
        "${String.format("%.2f", mrd)} Mrd"
    } else if (this > 1000000) {
        val mio = (this / 1000000)
        "${String.format("%.2f", mio)} Mio"
    } else {
        return ""
    }
}

fun Double.toAmount(): String {
    val result = convertNumber()
    return result.ifBlank { toString() }
}

fun Long.toAmount(): String {
    val result = toDouble().convertNumber()
    return result.ifBlank { toString() }
}

fun Int.toAmount(): String {
    val result = toDouble().convertNumber()
    return result.ifBlank { toString() }
}

fun Double.toMoney(): String {
    return "${toAmount()} $UNIT_CURRENCY"
}

fun Long.toMoney(): String {
    return "${toAmount()} $UNIT_CURRENCY"
}

fun Int.toMoney(): String {
    return "${toAmount()} $UNIT_CURRENCY"
}