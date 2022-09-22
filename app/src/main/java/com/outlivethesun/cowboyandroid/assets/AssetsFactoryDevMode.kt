package com.outlivethesun.cowboyandroid.assets

import com.outlivethesun.cowboyandroid.resources.*

class AssetsFactoryDevMode : IAssetsFactory {
    override fun create(): IAssets {
        val resources = listOf(
            Asset(Land, 1000),
            Asset(Horse, 55000000),
            Asset(Cow, 0),
            Asset(Milk, 0),
            Asset(Chicken, 80),
            Asset(ChickenEgg, 0),
            Asset(Goose, 0),
            Asset(Feather, 0),
            Asset(Pig, 0),
            Asset(Sheep, 0),
            Asset(Wool, 0)
        )
        return Assets(5550000000.00, resources)
    }
}