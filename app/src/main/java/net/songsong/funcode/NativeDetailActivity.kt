package net.songsong.funcode

import android.annotation.TargetApi
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.transition.Explode
import android.transition.Fade
import android.transition.Slide
import android.view.Window

class NativeDetailActivity : AppCompatActivity()
{

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        //setTransition 必須 在 setContentView之前
        setupTransition()

        setContentView(R.layout.activity_native_detail)
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private fun setupTransition()
    {
        //在這裡執行requestFeature or 在/values/styles 中加入
        //window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)

        when(intent.getStringExtra("flag"))
        {
            "explode" ->
            {
                val explodeTransition = Explode()
                explodeTransition.duration = 1000
                window.enterTransition = explodeTransition
                window.exitTransition = explodeTransition
            }

            "slide" ->
            {
                val slideTransition = Slide()
                slideTransition.duration = 1000
                window.enterTransition = slideTransition
                window.exitTransition = slideTransition
            }

            "fade" ->
            {
                val fadeTransition = Fade()
                fadeTransition.duration = 1000
                window.enterTransition = fadeTransition
                window.exitTransition = fadeTransition
            }
        }

    }
}
