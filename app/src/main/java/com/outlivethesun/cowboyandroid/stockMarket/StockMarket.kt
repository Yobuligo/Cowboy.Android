package com.outlivethesun.cowboyandroid.stockMarket

import com.outlivethesun.cowboyandroid.randomizer.randomizer
import com.outlivethesun.cowboyandroid.resources.IResource
import com.outlivethesun.cowboyandroid.round.Move

object StockMarket : IStockMarket {
    private val cache = hashMapOf<IResource, Double>()

    init {
        Move.registerOnNext {
            cache.clear()
        }
    }

    override fun getResourcePrice(resource: IResource): Double {
        val price = cache[resource]
        if (price != null) {
            return price
        }
        return addPriceAndReturn(resource)
    }

    private fun addPriceAndReturn(resource: IResource): Double {
        val price = randomizer.nextLong(resource.minPrice.toLong(), resource.maxPrice.toLong()).toDouble()
        cache[resource] = price
        return price
    }
}