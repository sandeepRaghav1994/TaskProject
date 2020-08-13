package bcr.Covid.viewmodel

import android.content.Context

/**
 * Created by sandeep on 9/8/20.
 */


class OffersViewModel(var context: Context?, var offersListener: OffersListener): BaseViewModel{




    interface OffersListener{



    }

    override fun destroy() {

    }



}