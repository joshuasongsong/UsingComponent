package net.songsong.funcode

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_home2.*

class Home3Activity : AppCompatActivity()
{
    private val homeButtonHandler = View.OnClickListener { v ->
        val intentHomeActivity = Intent(this,BottomNavigationActivity::class.java)
        intentHomeActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)

        startActivity(intentHomeActivity)
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home3)

        title="Home 3"

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        Button_Home.setOnClickListener(homeButtonHandler)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean
    {
        onBackPressed()
        return true
    }
}
