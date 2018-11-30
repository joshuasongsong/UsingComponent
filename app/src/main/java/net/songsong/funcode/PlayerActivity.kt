package net.songsong.funcode

import android.animation.ObjectAnimator
import android.annotation.TargetApi
import android.media.MediaPlayer
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_player.*

class PlayerActivity : AppCompatActivity()
{
    lateinit var mediaPlayer: MediaPlayer
    lateinit var birdAnimator: ObjectAnimator

    private var isSeeking = false

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)
        title = "Bird Records"

        setupView()
    }

    override fun onPause()
    {
        super.onPause()

        stopPlayer()

        resetAnimateBirdImageView()
    }

    private fun setupView()
    {
        mediaPlayer = MediaPlayer.create(this, R.raw.audio_bird)

        birdAnimator = ObjectAnimator.ofFloat(birdImageView, "rotation", 0.0f, 360f)
        birdAnimator.duration = 3000
        birdAnimator.repeatCount = ObjectAnimator.INFINITE
        birdAnimator.repeatMode = ObjectAnimator.RESTART

        SeekBar_Progress.max = mediaPlayer.duration

        mediaPlayer.setVolume(25 / 100f, 25 / 100f)

        Button_Play.setOnClickListener(playButtonClickHandler)
        Button_Stop.setOnClickListener(stopButtonClickHandler)

        SeekBar_Progress.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener
        {

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean)
            {
                if (isSeeking)
                {
                    mediaPlayer.seekTo(SeekBar_Progress.progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?)
            {
                isSeeking = true
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?)
            {
                isSeeking = false
            }
        })

        SeekBar_Volume.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener
        {

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean)
            {
                TextView_Volume.text = "Volumn: ${SeekBar_Volume.progress}%"
                mediaPlayer.setVolume(progress / 100f, progress / 100f)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?)
            {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?)
            {
            }
        })

        mediaPlayer.setOnCompletionListener {
            stopPlayer()
        }

        val thread = Thread(Runnable
        {
            while (true)
            {
                Thread.sleep(500)
                if (!isSeeking)
                {
                    SeekBar_Progress.progress = mediaPlayer.currentPosition
                }
            }
        })
        thread.start()
    }

    private val playButtonClickHandler = View.OnClickListener { v ->

        if (mediaPlayer.isPlaying)
        {
            pausePlayer()
        }
        else
        {
            startPlayer()
        }
    }

    private var stopButtonClickHandler = View.OnClickListener { v ->
        stopPlayer()
    }

    private fun startPlayer()
    {
        //播放完畢，則從頭播放
        if (mediaPlayer.currentPosition == mediaPlayer.duration)
        {
            mediaPlayer.seekTo(0)
        }
        mediaPlayer.start()
        Button_Play.text = getString(R.string.pause)

        startAnimateBirdImageView()
    }

    private fun pausePlayer()
    {
        mediaPlayer.pause()

        Button_Play.text = getString(R.string.play)

        pauseAnimateBirdImageView()
    }

    private fun stopPlayer()
    {
        mediaPlayer.pause()
        mediaPlayer.seekTo(0)

        SeekBar_Progress.progress = 0

        Button_Play.text = getString(R.string.play)

        resetAnimateBirdImageView()
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private fun startAnimateBirdImageView()
    {
        if (birdAnimator.isPaused)
        {
            birdAnimator.resume()
        }
        else
        {
            birdAnimator.start()
        }
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private fun pauseAnimateBirdImageView()
    {
        birdAnimator.pause()
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private fun resetAnimateBirdImageView()
    {
        birdAnimator.end()
    }
}
