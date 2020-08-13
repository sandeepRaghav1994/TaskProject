package bcr.Covid.view

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * Created by sandeep on 9/8/20.
 */
class MyFragmentPagerAdapter(private val myContext: Context, fm:FragmentManager) : FragmentPagerAdapter(fm) {

    // this is for fragment tabs


    private var mList = mutableListOf<Fragment>()
    private val mTitleList = mutableListOf<String>()



    override fun getItem(i: Int): Fragment {
        return mList.get(i)
    }

    override fun getCount(): Int {
        return mList.size
    }

    fun addFragment(fragment: Fragment, title: String) {
        mList.add(fragment)
        mTitleList.add(title)
    }

    override fun getPageTitle(position: Int): CharSequence {
        return mTitleList.get(position)
    }

}