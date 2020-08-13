package bcr.Covid.network

import bcr.Covid.model.Response
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Created by sandeep on 8/8/20.
 */

interface ApiService {


    @GET("testing_data")
    fun getRemoteData(): Observable<Response>

}