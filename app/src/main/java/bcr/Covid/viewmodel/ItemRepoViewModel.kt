package bcr.Covid.viewmodel

import android.content.Context
import android.databinding.BaseObservable
import android.databinding.ObservableField
import android.widget.Toast
import bcr.Covid.model.Cupons

/**
 * Created by sandeep on 9/8/20.
 */

class ItemRepoViewModel(var context: Context, var cupons: Cupons): BaseObservable() {

    var title: ObservableField<String> = ObservableField(cupons.title)
    var description: ObservableField<String> = ObservableField( cupons.description)
    var price: ObservableField<String> = ObservableField("Rs. "+cupons.price)

    fun onItemClick(price:String) {

        Toast.makeText(context, "Congratulations!  you got the cupon of Rs. "+price, Toast.LENGTH_SHORT).show()
    }

    companion object {
        fun setRepository(itemViewModel: ItemRepoViewModel, cupons: Cupons){
            itemViewModel.cupons = cupons
            itemViewModel.notifyChange()
        }
    }
}
