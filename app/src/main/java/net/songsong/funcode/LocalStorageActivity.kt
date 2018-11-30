package net.songsong.funcode

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_local_storage.*

class LocalStorageActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_local_storage)

        setupView()
    }

    private var saveButtonClickHandler = View.OnClickListener { v ->

        val name = EditText_KeyName.text.toString()

        if(name.isEmpty())
        {
            Toast.makeText(this,"please type name", Toast.LENGTH_SHORT).show()
            return@OnClickListener
        }

        val preference = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = preference.edit()
        editor.putString("Login_Name",name)
        editor.apply()

        Toast.makeText(this,"did save name as $name", Toast.LENGTH_SHORT).show()
    }

    private var nextButtonClickHandler = View.OnClickListener { v ->
        val intentNameActivity = Intent(this,NameActivity::class.java)
        startActivity(intentNameActivity)
    }

    fun setupView()
    {
        Button_Save.setOnClickListener(saveButtonClickHandler)
        Button_Next.setOnClickListener(nextButtonClickHandler)
    }
}
