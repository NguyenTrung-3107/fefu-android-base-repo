package ru.fefu.activityapplication

import android.app.Application
import androidx.room.Room
import ru.fefu.activityapplication.rooms.MyDatabase

class App : Application() {
    companion object {
        lateinit var INSTANCE: App
    }

    val db: MyDatabase by lazy {
        Room.databaseBuilder(
            this,
            MyDatabase::class.java,
            "my_database"
        ).allowMainThreadQueries().build()
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }
}