package net.songsong.funcode

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import kotlinx.android.synthetic.main.activity_progress_control.*

class ProgressControlActivity : AppCompatActivity()
{
    private var currentProgress: Int = 0
    private var maxProgress = 100
    private var repeatTaskTime: Long = 2000

    private var taskHandler = Handler()
    private var runnable = object : Runnable
    {
        override fun run()
        {
            startIncreaseProgress()
        }
    }

    private var startButtonHandler = View.OnClickListener { v ->
        startIncreaseProgress()
    }

    private var pauseButtonHandler = View.OnClickListener { v ->
        pauseIncreasingProgress()
    }

    private var stopButtonHandler = View.OnClickListener { V ->
        resetProgress()
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progress_control)

        setupView()
    }

    private fun setupView()
    {
        Button_Start.setOnClickListener(startButtonHandler)
        Button_Pause.setOnClickListener(pauseButtonHandler)
        Button_Stop.setOnClickListener(stopButtonHandler)
    }

    private fun startIncreaseProgress()
    {
        if(currentProgress >= maxProgress)
        {
            pauseIncreasingProgress()
            return
        }
        else
        {
            increaseProgressBy(currentProgress)
        }

        println("post runnable")
        taskHandler.postDelayed(runnable,repeatTaskTime)
    }

    private fun increaseProgressBy(value:Int)
    {
        println("remove task handler")

        currentProgress += 10
        ProgressBar_ShowStatus.setProgress(currentProgress)

        updateProgressTextView()
    }

    private fun pauseIncreasingProgress()
    {
        println("remove task handler")

        taskHandler.removeCallbacksAndMessages(null)
    }

    private fun resetProgress()
    {
        println("reset progress")

        pauseIncreasingProgress()

        currentProgress = 0

        ProgressBar_ShowStatus.setProgress(0)

        updateProgressTextView()
    }

    private fun updateProgressTextView()
    {
        TextView_Progress.text = "$currentProgress%"
    }
}
