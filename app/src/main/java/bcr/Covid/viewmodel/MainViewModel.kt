package bcr.Covid.viewmodel

import android.content.Context
import android.databinding.ObservableBoolean
import android.databinding.ObservableField

import bcr.Covid.R
import bcr.Covid.model.Response
import bcr.Covid.repository.MainRepository
import io.reactivex.observers.DisposableObserver

/**
 * Created by sandeep on 9/8/20.
 */


class MainViewModel(var context: Context?, var repositoryListener: RepositoryListener): BaseViewModel(), MainRepository.DataListener {

    interface RepositoryListener {

        fun onError(error:String)
        fun getData(data:Response)
        fun onBackClick()
    }

    var infoMessage: ObservableField<String> = ObservableField<String>(context?.getString(R.string.default_info_message))
    var isInfo: ObservableBoolean = ObservableBoolean(true)
    var isProgress: ObservableBoolean = ObservableBoolean(false)
    var isEmpty: ObservableBoolean = ObservableBoolean(true)

    private var disposable: DisposableObserver<Response>? = null
    private var mainRespository: MainRepository = MainRepository(context!!, this)


    fun onBackClick(){

        repositoryListener?.onBackClick()
    }

    override fun destroy() {
        if(!disposable!!.isDisposed) disposable!!.dispose()
        disposable = null
        context = null
    }

    fun loadCovidData() {
        isProgress.set(true)
        isEmpty.set(true)
        isInfo.set(false)
        disposable = mainRespository.loadData()

    }

    override fun onError(message: String) {
        isProgress.set(false)
        infoMessage.set(message)
        isInfo.set(true)
        repositoryListener!!.onError(message)
    }

    override fun onComplete() {

        isProgress.set(false)

    }

    override fun onSuccess(value: Response?) {


        repositoryListener.getData(value!!)

        isProgress.set(false)
    }

}