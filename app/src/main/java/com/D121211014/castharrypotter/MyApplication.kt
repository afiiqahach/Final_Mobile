package com.D121211014.castharrypotter

import android.app.Application
import com.D121211014.castharrypotter.data.AppContainer
import com.D121211014.castharrypotter.data.DefaultAppContainer

class MyApplication:Application() {
    lateinit var container: AppContainer

    override fun onCreate(){
        super.onCreate()
        container = DefaultAppContainer()
    }
}