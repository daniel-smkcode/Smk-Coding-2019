package com.ir.smkcoding

import android.content.Context
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class tabLayoutAdapter
    (fm : FragmentManager, context: Context)
    : FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        val moviekFragment = MoviekFragment()

        val bundle = Bundle()
        when (position) {
            0 -> bundle.putString("KEY", "MOVIE")
            1 -> bundle.putString("KEY", "TV")
        }
        moviekFragment.arguments = bundle

        return moviekFragment
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "MOVIE"
            1 -> "TV"
            else -> "nothing"
        }
    }
}