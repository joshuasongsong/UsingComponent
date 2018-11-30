package net.songsong.funcode

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import kotlinx.android.synthetic.main.activity_image_slider.*

class ImageSliderActivity : AppCompatActivity()
{
    lateinit var pager: ViewPager

    val images: IntArray = intArrayOf(R.drawable.img_1, R.drawable.img_2, R.drawable.img_3, R.drawable.img_4, R.drawable.img_5)

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_slider)

        setupView()
    }

    private fun setupView()
    {
        val adapter: PagerAdapter = SliderPagerAdapter(this, images)
        pager = findViewById(R.id.ViewPager_Pager)
        pager.adapter = adapter

        pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener
        {
            override fun onPageScrollStateChanged(state: Int)
            {
                println("on page scroll state changed $state")
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int)
            {
                val currentPosition = position + 1
                TextView_PageCount.text = "$currentPosition/${images.size}"
            }

            override fun onPageSelected(position: Int)
            {
                println("on page selected $position")
            }
        })
    }
}
