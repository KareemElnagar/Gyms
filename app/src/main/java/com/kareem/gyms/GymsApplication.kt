package com.kareem.gyms

import android.app.Application
import android.content.Context

class GymsApplication : Application() {

    init {
        application = this
    }
    companion object {
        private lateinit var application : GymsApplication
        fun getApplicationContext() : Context = application.applicationContext
    }
}