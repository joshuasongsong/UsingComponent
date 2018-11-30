package net.songsong.funcode

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_google_voice.*

class GoogleVoiceActivity : AppCompatActivity()
{
    private val voiceRecognitionRequestCode = 1004

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_google_voice)

        setupView()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
    {
        if (requestCode == voiceRecognitionRequestCode && resultCode == Activity.RESULT_OK)
        {
            val matches = data!!.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            println("Recognition results $matches")

            //語音識別會有複筆資料，第一個是最精確的
            val ResultText = matches.first()

            TextView_Voice.text = ResultText

            updateTextViewWithText(ResultText)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun setupView()
    {
        Button_Recognition.setOnClickListener(recognitionButtonListener)
        TextView_Tips.setTextColor(Color.WHITE)
    }

    private fun startVoiceRecognitionActivity()
    {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)

        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)

        intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 5)

        startActivityForResult(intent, voiceRecognitionRequestCode)
    }

    private val recognitionButtonListener = View.OnClickListener { v ->
        startVoiceRecognitionActivity()
    }

    //根據語音識別內容改變文字顏色
    private fun updateTextViewWithText(text: String)
    {
        Log.e("update:", text)
        TextView_Bike.setTextColor(Color.BLACK)
        TextView_Climb.setTextColor(Color.BLACK)
        TextView_Swim.setTextColor(Color.BLACK)

        //平時tips用白色隱藏，除非使用者講的內容不在範圍才用黑色顯示
        TextView_Tips.setTextColor(Color.WHITE)

        if (text.contains("騎車", true))
        {
            TextView_Bike.setTextColor(Color.BLUE)
        }
        else if (text.contains("爬山", false))
        {
            TextView_Climb.setTextColor(Color.BLUE)
        }
        else if (text.contains("游泳", false))
        {
            TextView_Swim.setTextColor(Color.BLUE)
        }
        else
        {
            TextView_Tips.setTextColor(Color.BLUE)
        }
    }
}
