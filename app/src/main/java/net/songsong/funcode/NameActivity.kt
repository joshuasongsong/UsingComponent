package net.songsong.funcode

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_name.*

class NameActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_name)

        setupView()
    }

    private var showNameButtonClickHandler = View.OnClickListener { v ->

        val preference = PreferenceManager.getDefaultSharedPreferences(this)
        val name = preference.getString("Login_Name","")

        if(name.isEmpty())
        {
            Toast.makeText(this,"name is empty",Toast.LENGTH_SHORT).show()
        }
        TextView_Name.text = name
    }

    private fun setupView()
    {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        Button_ShowName.setOnClickListener(showNameButtonClickHandler)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean
    {
        when(item?.itemId)
        {
            android.R.id.home ->
            {
                onBackPressed()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }
}
