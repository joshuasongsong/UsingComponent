package net.songsong.funcode

import android.Manifest
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_record.*
import android.media.MediaRecorder
import android.media.MediaPlayer
import android.os.Environment
import android.support.v4.app.ActivityCompat
import android.util.Log
import android.widget.Toast
import java.io.File
import java.io.IOException

class RecordActivity : AppCompatActivity()
{
    var soundFile: File? = null
    var isRecording = false

    lateinit var recorder: MediaRecorder
    var player: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record)

        setupView()
    }

    private fun setupView()
    {
        Button_Record.setOnClickListener(recordButtonClickHandler)
        Button_Play.setOnClickListener(playButtonClickHandler)
    }

    private val recordButtonClickHandler = View.OnClickListener { v ->

        if (isRecording)
        {
            Toast.makeText(this, "Stop Recording", Toast.LENGTH_SHORT).show()
            stopRecording()
        }
        else
        {
            Toast.makeText(this, "Start Recording", Toast.LENGTH_SHORT).show()
            startRecording()
        }
    }

    private val playButtonClickHandler = View.OnClickListener { v ->

        if (soundFile == null)
        {
            Toast.makeText(this, "Found sound file is null when click play", Toast.LENGTH_SHORT).show()
        }
        else
        {
            player = MediaPlayer()
            player!!.setOnPreparedListener(playerPreparedHandler)
            player!!.setOnCompletionListener { stopPlayer() }

            if (player!!.isPlaying)
            {
                player?.pause()
                Button_Play.text = R.string.play.toString()
            }
            else
            {
                Button_Play.text = R.string.pause.toString()
                try
                {
                    player?.setDataSource(soundFile!!.absolutePath)
                    player?.prepare()
                }
                catch (e: IOException)
                {
                    Log.e("PlayRecording", "Failed")
                }
            }
        }
    }

    private fun startRecording()
    {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.RECORD_AUDIO), 0)
            return
        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 0)
            return
        }
        else
        {
            val path = File(Environment.getExternalStorageDirectory().path)
            try
            {
                soundFile = File.createTempFile("birdRecording", ".3gp", path)
                println("create file $soundFile")
            }
            catch (e: IOException)
            {
                Log.e("Setup Sound File", "failed ${e.message}")
            }
        }
        if (soundFile == null)
        {
            Toast.makeText(this, "Sound file is null", Toast.LENGTH_SHORT).show()
            return
        }
        recorder = MediaRecorder()
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC)
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)

        recorder.setOutputFile(soundFile?.absolutePath)

        try
        {
            recorder.prepare()
        }
        catch (e: IOException)
        {
        }

        recorder.start()

        isRecording = true

        Button_Play.isEnabled = false
        Button_Record.text = "Stop Record"
    }

    private fun stopRecording()
    {
        Log.e("Recorder", "stop recording")

        recorder.stop()
        recorder.release()

        isRecording = false
        Button_Play.isEnabled = true
        Button_Record.text = R.string.record.toString()
    }

    private var playerPreparedHandler = MediaPlayer.OnPreparedListener {
        player?.start()
    }

    private fun stopPlayer()
    {
        player?.release()
        player = null
        Button_Play.text = R.string.play.toString()
    }
}
