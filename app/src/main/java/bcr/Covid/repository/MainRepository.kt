package bcr.Covid.repository

import android.app.Application
import android.content.Context
import android.widget.Toast
import bcr.Covid.App
import bcr.Covid.R
import bcr.Covid.model.Response
import bcr.Covid.network.ApiService
import bcr.Covid.network.ServiceFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver

/**
 * Created by rumahulya on 18/04/18.
 */

class MainRepository(var context: Context, var dataListener: DataListener) {

    interface DataListener {
        fun onError(message: String)
        fun onComplete()
        fun onSuccess(value: Response?)
    }

    private var factory: ServiceFactory = ServiceFactory()
    private var api: ApiService = factory.provideApi()

    /**
     * load repositories
     */
   /* fun loadRepositories(username : String): DisposableObserver<List<Repository>>? {
        return api.getRepositories(username)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(App.instance?.defaultSubscribeScheduler())
                .subscribeWith(LongOperationObserver())
    }*/


    fun loadData(): DisposableObserver<Response>? {
        return api.getRemoteData()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(App.instance?.defaultSubscribeScheduler())
                .subscribeWith(CovidResponseObserver())

    }


    private inner class CovidResponseObserver: DisposableObserver<Response>() {



        override fun onComplete() {
            dataListener.onComplete()
        }

        override fun onError(e: Throwable?) {

           // Toast.makeText(context?.applicationContext,context?.getString(R.string.error_username_not_found),Toast.LENGTH_LONG)
           // Log.e("error :",e?.printStackTrace().toString())
            dataListener.onError(context?.getString(R.string.error_username_not_found))
        }

       override fun onNext(value: Response?) {
            dataListener.onSuccess(value)
        }

    }

}