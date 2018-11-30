package net.songsong.funcode

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class ParksAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager)
{
    override fun getCount(): Int
    {
        return 6
    }

    override fun getItem(position: Int): Fragment
    {
        when (position)
        {
            0 -> return Park1Fragment()
            1 -> return Park2Fragment()
            2 -> return Park3Fragment()
            3 -> return Park4Fragment()
            4 -> return Park5Fragment()
            5 -> return Park6Fragment()
            else -> return Park6Fragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence
    {
        when (position)
        {
            0 -> return "Park 1"
            1 -> return "Park 2"
            2 -> return "Park 3"
            3 -> return "Park 4"
            4 -> return "Park 5"
            5 -> return "Park 6"
            else -> return "Park 6"
        }
    }
}