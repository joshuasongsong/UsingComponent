package net.songsong.funcode

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity()
{
    lateinit var drawer: DrawerLayout
    lateinit var toolbar: Toolbar
    lateinit var navigationView: NavigationView
    lateinit var fragmentmanager: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        setupView()
    }

        private fun setupView()
    {
        drawer = findViewById(R.id.DrawerLayout_Detail)
        toolbar = findViewById(R.id.toolbar)
        navigationView = findViewById(R.id.nvView)

        navigationView.setNavigationItemSelectedListener(navigationItemSelectedListener)

        val toggle = ActionBarDrawerToggle(this, drawer, toolbar, R.string.drawer_open, R.string.drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()
        setSupportActionBar(toolbar)

        fragmentmanager = supportFragmentManager
        fragmentmanager.beginTransaction().replace(R.id.FrameLayout_Content, Chicken1Fragment()).commit()
    }

    override fun onBackPressed()
    {
        if (drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START)
        }
        else
        {
            super.onBackPressed()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean
    {
        when (item?.itemId)
        {
            android.R.id.home -> drawer.openDrawer(GravityCompat.START)
        }
        return super.onOptionsItemSelected(item)
    }

    private var navigationItemSelectedListener = NavigationView.OnNavigationItemSelectedListener { item: MenuItem ->
        Log.e("selectDraweritem", "on ${item.itemId}")

        when (item.itemId)
        {
            R.id.Nav_1_Fragment ->
            {
                val fragment: Fragment = Chicken1Fragment()
                fragmentmanager.beginTransaction().replace(R.id.FrameLayout_Content, fragment).commit()
            }

            R.id.Nav_2_Fragment ->
            {
                val fragment: Fragment = Chicken2Fragment()
                fragmentmanager.beginTransaction().replace(R.id.FrameLayout_Content, fragment).commit()
            }

            R.id.Nav_3_Fragment ->
            {
                val fragment: Fragment = Chicken3Fragment()
                fragmentmanager.beginTransaction().replace(R.id.FrameLayout_Content, fragment).commit()
            }

            R.id.Nav_4_Fragment ->
            {
                val fragment: Fragment = Chicken4Fragment()
                fragmentmanager.beginTransaction().replace(R.id.FrameLayout_Content, fragment).commit()
            }

            R.id.Nav_5_Fragment ->
            {
                val fragment: Fragment = Chicken5Fragment()
                fragmentmanager.beginTransaction().replace(R.id.FrameLayout_Content, fragment).commit()
            }

            R.id.Nav_6_Fragment ->
            {
                val fragment: Fragment = Chicken6Fragment()
                fragmentmanager.beginTransaction().replace(R.id.FrameLayout_Content, fragment).commit()
            }

            R.id.Nav_7_Fragment ->
            {
                val fragment: Fragment = Chicken7Fragment()
                fragmentmanager.beginTransaction().replace(R.id.FrameLayout_Content, fragment).commit()
            }
        }
        item.isChecked = true
        title = item.title
        drawer.closeDrawers()
        true
    }
}
