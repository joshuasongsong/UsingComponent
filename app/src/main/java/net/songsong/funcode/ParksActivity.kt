package net.songsong.funcode

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager

class ParksActivity : AppCompatActivity()
{
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager
    private lateinit var adapter: ParksAdapter

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parks)
    }

    private fun setupView()
    {
        //adapter
        adapter = ParksAdapter(supportFragmentManager)

        //Layoutinflater
        val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE)

        //viewPager
        viewPager = findViewById(R.id.ViewPager_LayoutMain)
        viewPager.adapter = adapter

        //tabLayout
        tabLayout = findViewById(R.id.TabLayout_LayoutMain)

        //Link TabLayout with viewPager
        tabLayout.setupWithViewPager(viewPager)
    }
}
