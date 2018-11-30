package net.songsong.funcode

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_little_bird_sound.*

class LittleBirdSoundActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_little_bird_sound)

        setupView()
    }

    private fun setupView()
    {
        Button_Player.setOnClickListener(playerButtonClickHandler)
        Button_Recorder.setOnClickListener(recorderButtonClickHandler)
    }

    private val playerButtonClickHandler = View.OnClickListener { v ->

        val intent = Intent(this, PlayerActivity::class.java)
        startActivity(intent)
    }

    private val recorderButtonClickHandler = View.OnClickListener { v ->

        val intent = Intent(this, RecordActivity::class.java)
        startActivity(intent)
    }
}
