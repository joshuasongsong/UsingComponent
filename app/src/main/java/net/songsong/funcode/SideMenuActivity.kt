package net.songsong.funcode

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_side_menu.*

class SideMenuActivity : AppCompatActivity()
{

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_side_menu)
    }

    private val goButtonClickHandler = View.OnClickListener { v ->

        val intent = Intent(this,DetailActivity::class.java)
        startActivity(intent)
    }

    private fun setupView()
    {
        Button_GO.setOnClickListener(goButtonClickHandler)
    }
}
