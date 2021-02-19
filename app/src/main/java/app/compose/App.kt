package app.compose

import android.app.Application
import com.mooveit.library.Fakeit

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Fakeit.init()
    }
}