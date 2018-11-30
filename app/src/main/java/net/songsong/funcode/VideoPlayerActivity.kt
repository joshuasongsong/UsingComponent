package net.songsong.funcode

import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.VideoView
import kotlinx.android.synthetic.main.activity_video_player.*

class VideoPlayerActivity : AppCompatActivity()
{
    private lateinit var videoView: VideoView
    private var isSeeking = false

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_player)

        setupView()
        setupVideoView()
    }

    private fun setupView()
    {
        Button_MainPlay.setOnClickListener(playButtonClickHandler)
        Button_MainPause.setOnClickListener(pauseButtonClickHandler)
        Button_MainStop.setOnClickListener(stopButtonClickHandler)
    }

    private fun setupVideoView()
    {
        videoView = findViewById(R.id.VideoView_Layout)
        videoView.setVideoURI(Uri.parse("android.resource://" + packageName + "/" + R.raw.short_video))
        videoView.start()

        videoView.setMediaController(null)
    }

    private val playButtonClickHandler = View.OnClickListener {
        videoView.start()
    }

    private val pauseButtonClickHandler = View.OnClickListener {
        videoView.pause()
    }

    private val stopButtonClickHandler = View.OnClickListener {
        videoView.seekTo(0)
        videoView.pause()
    }

}
