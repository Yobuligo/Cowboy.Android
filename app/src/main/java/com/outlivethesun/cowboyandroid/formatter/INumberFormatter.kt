package com.outlivethesun.cowboyandroid.formatter

interface INumberFormatter {
    fun toMoney(amount: Double): String
    fun toMoney(amount: Long): String
    fun toMoney(amount: Int): String
    fun toAmount(amount: Long): String
    fun toAmount(amount: Double): String
}