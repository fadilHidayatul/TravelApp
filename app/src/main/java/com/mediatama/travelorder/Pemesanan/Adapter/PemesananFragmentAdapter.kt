package com.mediatama.travelorder.Pemesanan.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import java.util.*

class PemesananFragmentAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    private val fragmentList: MutableList<Fragment> = ArrayList()
    private val stringListFragment: MutableList<String> = ArrayList()


    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getCount(): Int {
        return fragmentList.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return stringListFragment[position]
    }

    fun addFragment(fragment: Fragment, title: String) {
        fragmentList.add(fragment)
        stringListFragment.add(title)
    }
}