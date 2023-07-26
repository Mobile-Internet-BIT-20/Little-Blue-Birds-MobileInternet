package com.lwy.bluebird.Fragment.profile

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ProfileAdapter(
    val fragment: Fragment,
    val fragmentList: List<Fragment>,
    val titleList: List<String>
    ): FragmentStateAdapter(fragment) {
    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    fun getPageTitle(position: Int): CharSequence?{
        return titleList[position]
    }

}