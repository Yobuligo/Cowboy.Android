package com.outlivethesun.cowboyandroid.stockMarket

import com.outlivethesun.cowboyandroid.resources.IResource

interface IStockMarket {
    fun getResourcePrice(resource: IResource): Double
}