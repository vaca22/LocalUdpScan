package com.vaca.localudpscan

import android.app.Application



class MainApplication : Application() {

    companion object {
        lateinit var application: Application
    }


    override fun onCreate() {
        super.onCreate()



        application = this

    }


}