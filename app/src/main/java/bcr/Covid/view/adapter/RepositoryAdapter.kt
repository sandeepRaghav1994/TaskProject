package bcr.Covid.view.adapter

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import bcr.Covid.R

import bcr.Covid.databinding.ItemRepoBinding
import bcr.Covid.viewmodel.ItemRepoViewModel
import kotlin.collections.ArrayList
import bcr.Covid.model.Cupons


/**
 * Created by sandeep on 9/8/20.
 */

class RepositoryAdapter : RecyclerView.Adapter<RepositoryAdapter.ItemViewHolder>() {

    private var cupons: ArrayList<Cupons> = ArrayList<Cupons>()


    fun setRepositories(cupons:ArrayList<Cupons>) {

        this.cupons.clear()
        this.cupons.addAll(cupons)

    }

    override fun onBindViewHolder(holder: ItemViewHolder?, position: Int) {

        Log.e("onBindViewHolder","called")


             holder?.bindRepository(cupons.get(position))

    }

    override fun getItemCount(): Int {

        return cupons.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {

        return super.getItemViewType(position);
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int):ItemViewHolder {
        Log.e("onCreateViewHolder","called")


            var binding = DataBindingUtil.inflate<ItemRepoBinding>(LayoutInflater.from(parent?.context),
                    R.layout.item_repo, parent, false)
            return ItemViewHolder(binding)

    }


    class ItemViewHolder(var binding: ItemRepoBinding) : RecyclerView.ViewHolder(binding.cardView) {

       fun bindRepository(cupons: Cupons) {
            if (binding.vm == null){
                binding.vm = ItemRepoViewModel(itemView.context, cupons)

                itemView.setOnClickListener {  binding.vm!!.onItemClick(cupons.price) }
            } else {
                ItemRepoViewModel.setRepository(binding.vm!!, cupons)

            }


       }
    }


}