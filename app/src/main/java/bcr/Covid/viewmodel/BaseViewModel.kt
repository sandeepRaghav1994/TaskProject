package bcr.Covid.viewmodel

import android.arch.lifecycle.ViewModel

/**
 * Created by sandeep on 8/8/20.
 */

abstract class BaseViewModel:ViewModel(){

  abstract  fun destroy()

}