package com.jv23.echojournal

import android.app.Application
import com.jv23.echojournal.di.AppContainer
import com.jv23.echojournal.di.AppDataContainer

class EchoJournalApplication: Application(){

    companion object {
        lateinit var container: AppContainer
    }

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this@EchoJournalApplication)
    }

}