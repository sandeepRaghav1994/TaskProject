package bcr.Covid.view.fragment

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import bcr.Covid.R
import bcr.Covid.databinding.FragmentDetailsBinding
import bcr.Covid.viewmodel.DetailsViewModel
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

/**
 * Created by sandeep on 9/8/20.
 */
class Details : Fragment() {

    private lateinit var binding: FragmentDetailsBinding

    private lateinit var viewModel: DetailsViewModel

    lateinit var title_tv: TextView
    lateinit var desc_tv: TextView
    lateinit var imgV: ImageView
   lateinit var prog_bar:ProgressBar


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view: View? = inflater?.inflate(R.layout.fragment_details, container, false)

        title_tv=view!!.findViewById(R.id.title_tv)
        desc_tv=view.findViewById(R.id.desc_tv)
        imgV=view.findViewById(R.id.imgV)

      prog_bar=view.findViewById(R.id.progress)


        prog_bar.setVisibility(View.VISIBLE)
        if (getArguments() != null) {


            setData(arguments)

        }

        return view
    }

    fun setData(bundle: Bundle){

        title_tv.setText(bundle.getString("title"))
        desc_tv.setText(bundle.getString("desc"))


        Picasso.with(context).load(bundle.getString("url")).noFade().into(imgV, object : Callback {
            override fun onSuccess() {
                prog_bar.setVisibility(View.GONE)
            }

            override fun onError() {

            }
        })
    }


   /* private fun setupBinding() {

        viewModel   = DetailsViewModel(context)
        binding.vm  = viewModel
    }

*/
    override fun onDestroy() {
        super.onDestroy()
       // viewModel.destroy()
    }


}


