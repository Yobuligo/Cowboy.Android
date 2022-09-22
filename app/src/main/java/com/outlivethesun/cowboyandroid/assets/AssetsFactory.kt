package com.outlivethesun.cowboyandroid.assets

import com.outlivethesun.cowboyandroid.resources.*

class AssetsFactory : IAssetsFactory {
    override fun create(): IAssets {
        val resources = listOf(
            Asset(Land, 1000),
            Asset(Horse, 1),
            Asset(Cow, 5),
            Asset(Milk, 0),
            Asset(Chicken, 100),
            Asset(ChickenEgg, 0),
            Asset(Goose, 2),
            Asset(Feather, 0),
            Asset(Pig, 10),
            Asset(Sheep, 10),
            Asset(Wool, 0)
        )
        return Assets(10000.00, resources)
    }
}