package bcr.Covid.view.fragment

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import bcr.Covid.R
import android.support.v4.view.ViewPager
import android.widget.ProgressBar
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.squareup.picasso.RequestCreator
import java.net.URL


/**
 * Created by sandeep on 9/8/20.
 */
class ImageAdapter():PagerAdapter() {


    private var context: Context? = null
    private var layoutInflater: LayoutInflater? = null
    private lateinit var banners:ArrayList<String>
    private val images = arrayOf<Int>(R.drawable.ic_launcher_foreground,R.drawable.ic_launcher_background,R.drawable.ic_launcher_foreground)
    private  var images1 = mutableListOf<RequestCreator>()


    constructor(context:Context,banners:ArrayList<String>):this(){

        this.context=context
        this.banners=banners
    }

    override fun isViewFromObject(view: View?, `object`: Any?): Boolean {
         //To change body of created functions use File | Settings | File Templates.
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup?, position: Int):Any {


        for(i in 0 until banners.size){

            images1.add(Picasso.with(context).load(banners[i]).noFade())

        }

        layoutInflater = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater!!.inflate(R.layout.custom_layout, null)
        val imageView = view.findViewById<View>(R.id.imageView) as ImageView

        val prog_bar = view.findViewById<View>(R.id.progress) as ProgressBar

        prog_bar.setVisibility(View.VISIBLE)
        images1[position].into(imageView, object : Callback {
            override fun onSuccess() {
                prog_bar.setVisibility(View.GONE)
            }

            override fun onError() {

            }
        })

        val vp = container as ViewPager
        vp.addView(view, 0)
        return view

    }

    override fun getCount(): Int {
      return  images.size

    }

    override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any?) {

        val vp = container as ViewPager
        val view = `object` as View
        vp.removeView(view)
    }
}