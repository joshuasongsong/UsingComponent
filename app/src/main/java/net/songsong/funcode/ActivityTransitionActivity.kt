package net.songsong.funcode

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair
import android.view.View
import kotlinx.android.synthetic.main.activity_transition.*

class ActivityTransitionActivity : AppCompatActivity()
{

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transition)

        setupView()
    }

    private fun setupView()
    {
        Button_Explode.setOnClickListener(explodeButtonClickHandler)
        Button_Slide.setOnClickListener(slideButtonClickHandler)
        Button_Fade.setOnClickListener(fadeButtonClickHandler)
        ImageView_Love.setOnClickListener(loveImageClickHandler)
    }

    private var explodeButtonClickHandler = View.OnClickListener { v ->
        startTransitionWithFlag("explode")
    }

    private var slideButtonClickHandler = View.OnClickListener { v ->
        startTransitionWithFlag("slide")
    }

    private var fadeButtonClickHandler = View.OnClickListener { v ->
        startTransitionWithFlag("fade")
    }

    private fun startTransitionWithFlag(flag:String)
    {
        val intent = Intent(this,NativeDetailActivity::class.java)
        intent.putExtra("flag",flag)
        startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle())

    }

    private var loveImageClickHandler = View.OnClickListener { v ->

        val intent = Intent(this,NativeDetailActivity::class.java)
        val sharedView1 = ImageView_Cat as View
        val sharedView2 = ImageView_Love as View

        val p1 = Pair(sharedView1,"catTransition")
        val p2 = Pair(sharedView2,"textTransition")
        val transitionActivityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(this,p1,p2)

        startActivity(intent, transitionActivityOptions.toBundle())
    }
}
