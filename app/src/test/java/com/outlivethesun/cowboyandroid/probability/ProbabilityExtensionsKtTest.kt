package com.outlivethesun.cowboyandroid.probability

import junit.framework.TestCase

class ProbabilityExtensionsKtTest : TestCase() {
    fun testDoublePercent() {
        val testObject = 12.0
        assertEquals(1.2, testObject.percent(10))
    }

    fun testDoublePercentZero() {
        val testObject = 12.0
        assertEquals(0.0, testObject.percent(0))
    }

    fun testDoublePercentRange() {
        val testObject = 12.0
        val percent = testObject.percentRange(40, 50)
        assertTrue(percent > 4.8 && percent < 6)
    }

    fun testLongPercent() {
        val testObject: Long = 12
        assertEquals(1, testObject.percent(10))
    }

    fun testLongPercentRoundUp() {
        val testObject: Long = 12
        assertEquals(5, testObject.percent(40))
    }

    fun testLongPercentRoundEquals() {
        val testObject: Long = 12
        assertEquals(6, testObject.percent(50))
    }

    fun testLongPercentRoundDown() {
        val testObject: Long = 12
        assertEquals(8, testObject.percent(70))
    }

    fun testLongPercentZero() {
        val testObject: Long = 12
        assertEquals(0, testObject.percent(0))
    }

    fun testLongPercentRangeRoundUp() {
        val testObject: Long = 12
        assertEquals(8, testObject.percentRange(60, 60))
    }

    fun testLongPercentRangeRoundEquals() {
        val testObject: Long = 12
        assertEquals(6, testObject.percentRange(50, 50))
    }

    fun testLongPercentRangeRoundRoundDown() {
        val testObject: Long = 12
        assertEquals(9, testObject.percentRange(80, 80))
    }
}