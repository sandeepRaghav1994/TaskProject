package bcr.Covid.view

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.widget.Toast

import bcr.Covid.R
import bcr.Covid.databinding.ActivityMainBinding
import bcr.Covid.model.Response
import bcr.Covid.viewmodel.MainViewModel
import bcr.Covid.view.fragment.Details
import bcr.Covid.view.fragment.Offers


class MainActivity : AppCompatActivity(), MainViewModel.RepositoryListener {

    override fun onError(error:String) {

        Toast.makeText(applicationContext,error,Toast.LENGTH_LONG).show()
    }


    private lateinit var resp:Response

    override fun getData(data: Response) {

        resp=data

        setUpViewPager()
       // Details().detailsData(data.result.decription_image,data.result.description_body,data.result.description_title)


    }




    private lateinit var mviewpager: ViewPager
    private lateinit var tabs: TabLayout

    override fun onBackClick() {

        super.onBackPressed()

    }

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setupBinding()

        callApi()

    }


    private fun setUpViewPager(){

        tabs = /*findViewById(R.id.tabLayout)*/binding.tabLayout
        mviewpager = /*findViewById(R.id.viewPager)*/binding.viewPager


        val adapter = MyFragmentPagerAdapter(this,supportFragmentManager)
        adapter.addFragment(frag1(), "ONE")
        adapter.addFragment(frag2(), "TWO")
        mviewpager.setAdapter(adapter)
        tabs.setupWithViewPager(mviewpager)


    }


    private fun callApi() {

        viewModel.loadCovidData()

    }

    private fun setupBinding() {
        viewModel   = MainViewModel(this, this)
        binding.vm  = viewModel
    }

    override fun onDestroy() {
        super.onDestroy()
       // if(viewModel!=null)
        viewModel.destroy()
    }

    fun frag1():Fragment{


        val b = Bundle()

        b.putSerializable("banners",resp.result.banner)

        b.putSerializable("cupons",resp.result.cupons)
        b.putString("lat",resp.result.latitudes)
        b.putString("long",resp.result.longitude)

        val obj_offers = Offers()

        obj_offers.setArguments(b)

        return obj_offers
    }

    fun frag2():Fragment{

        val b = Bundle()

        b.putString("url",resp.result.decription_image)
        b.putString("title",resp.result.description_title)
        b.putString("desc",resp.result.description_body)

        val obj_details = Details()

        obj_details.setArguments(b)

        return obj_details
    }

}

