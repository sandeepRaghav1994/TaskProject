package bcr.Covid

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

/**
 * Created by sandeep on 8/8/20.
 */

class App: Application() {

    private var defaultSubscribeScheduler: Scheduler? = null

    companion object {
        var instance: App? = null
            private set

        fun hasNetwork(): Boolean {
            return instance!!.checkIfHasNetwork()
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance    = this
    }

    @SuppressLint("MissingPermission")
    fun checkIfHasNetwork(): Boolean {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = cm.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

    fun defaultSubscribeScheduler(): Scheduler? {
        if (defaultSubscribeScheduler == null) {
            defaultSubscribeScheduler = Schedulers.io()
        }
        return defaultSubscribeScheduler
    }

}
