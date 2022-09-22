package com.outlivethesun.cowboyandroid.stockMarket

import androidx.lifecycle.ViewModel

class ProfileViewModel : ViewModel() {
    val DEFAULT_USERNAME = "John Wayne"
    var username: String = DEFAULT_USERNAME
}