package bcr.Covid.view.fragment

import android.app.Application
import android.databinding.DataBindingUtil
import android.media.Image
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import bcr.Covid.R
import bcr.Covid.databinding.FragmentDetailsBinding
import bcr.Covid.databinding.FragmentOffersBinding
import bcr.Covid.viewmodel.DetailsViewModel
import bcr.Covid.viewmodel.OffersViewModel
import android.support.v4.view.ViewPager
import android.widget.ImageView
import android.widget.LinearLayout
import bcr.Covid.R.id.viewPager
import android.support.v4.content.ContextCompat
import android.databinding.adapters.ImageViewBindingAdapter.setImageDrawable
import android.databinding.adapters.ImageViewBindingAdapter.setImageDrawable
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Button
import bcr.Covid.model.Cupons
import bcr.Covid.view.adapter.RepositoryAdapter
import android.R.raw
import android.media.MediaPlayer
import android.util.Log
import android.widget.Toast


/**
 * Created by sandeep on 9/8/20.
 */
class Offers : Fragment(),OffersViewModel.OffersListener,View.OnClickListener {


    override fun onClick(p0: View?) {

        when (p0!!.getId()) {
            R.id.map ->
                Toast.makeText(activity.applicationContext,"map clicked.",Toast.LENGTH_SHORT).show()
            R.id.call ->
                Toast.makeText(activity.applicationContext,"call clicked.",Toast.LENGTH_SHORT).show()

            R.id.menu ->
                Toast.makeText(activity.applicationContext,"menu clicked.",Toast.LENGTH_SHORT).show()
        }

    }


    private lateinit var binding: FragmentOffersBinding

    private lateinit var viewModel: OffersViewModel
    private lateinit var repositoryAdapter: RepositoryAdapter
    private lateinit var rview: RecyclerView
    private lateinit var map: Button
    private lateinit var call: Button
    private lateinit var menu: Button

    var viewPager: ViewPager? = null
    private var dotscount: Int = 0
    private var dots: Array<ImageView?>? = null
    var sliderDotspanel: LinearLayout? = null
    private lateinit var cupons:ArrayList<Cupons>

    private lateinit var banners:ArrayList<String>
    private lateinit var lat:String
    private lateinit var long:String


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        Log.e("oncOfOffers","called")

        val view: View? = inflater!!.inflate(R.layout.fragment_offers, container, false)


        //RV code
        rview =  view!!.findViewById<RecyclerView>(R.id.repos_recycler_view)

        viewPager = view!!.findViewById(R.id.viewPager) as ViewPager


        map= view!!.findViewById(R.id.map) as Button
        call= view!!.findViewById(R.id.call) as Button
        menu= view!!.findViewById(R.id.menu) as Button


        map.setOnClickListener(this)
        call.setOnClickListener(this)
        menu.setOnClickListener(this)

        repositoryAdapter = RepositoryAdapter()
        rview.adapter = repositoryAdapter
        rview.layoutManager = LinearLayoutManager(activity)


        if (getArguments() != null) {


            cupons=arguments.getSerializable("cupons") as ArrayList<Cupons>

            banners=arguments.getSerializable("banners") as ArrayList<String>
            lat=arguments.getString("lat")
            long=arguments.getString("long")

        }


        sliderDotspanel = view.findViewById(R.id.SliderDots) as LinearLayout


        val imageAdapter = ImageAdapter(activity,banners)

        viewPager!!.setAdapter(imageAdapter)

        dotscount = imageAdapter.getCount()

        dots = arrayOfNulls<ImageView>(dotscount)


        for (i in 0 until dotscount) {
            dots!![i] = ImageView(activity)
            dots!![i]!!.setImageDrawable(ContextCompat.getDrawable(activity.applicationContext, R.drawable.non_active_dot))

            val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)

            params.setMargins(8, 0, 8, 0)

            sliderDotspanel!!.addView(dots!![i], params)

        }
        dots!![0]?.setImageDrawable(ContextCompat.getDrawable(activity.applicationContext, R.drawable.active_dot));


        viewPager?.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {

                for (i in 0 until dotscount) {

                    dots!![i]?.setImageDrawable(ContextCompat.getDrawable(
                            activity.applicationContext, R.drawable.non_active_dot))
                }

                dots!![position]?.setImageDrawable(ContextCompat.getDrawable(activity.applicationContext, R.drawable.active_dot))

            }

        })


        repositoryAdapter?.setRepositories(cupons)
        repositoryAdapter?.notifyDataSetChanged()

        return view
    }


    override fun onDestroy() {
        super.onDestroy()

        viewModel.destroy()
    }



}