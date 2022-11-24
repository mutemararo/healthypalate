package com.example.healthypalate.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class DiaryPagerAdapter(fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    var fragList = mutableListOf<Fragment>()


    override fun getCount(): Int {
        return fragList.size
    }

    override fun getItem(position: Int): Fragment {
        return fragList.get(position)
    }

    fun addFragment(fragment:Fragment){
        fragList.add(fragment)
    }

}