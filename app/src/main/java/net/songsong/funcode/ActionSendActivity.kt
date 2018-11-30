package net.songsong.funcode

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_action_send.*

class ActionSendActivity : AppCompatActivity()
{

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_action_send)

        setupView()
    }

    private fun setupView()
    {
        Button_Layout_Main_Share.setOnClickListener(shareButtonClickHandler)
    }

    private var shareButtonClickHandler = View.OnClickListener { v ->

        val intent = Intent()
        intent.action = Intent.ACTION_SEND
        intent.putExtra(Intent.EXTRA_TEXT,EditText_Layout_Main_Share.text.toString())
        intent.type = "text/plain"
        startActivity(intent)
    }
}
