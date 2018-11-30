package animations

import android.animation.ObjectAnimator
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_animation2.*
import net.songsong.funcode.R

class Animation2Activity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animation2)

        setupView()
    }

    private val goButtonClickHandler = View.OnClickListener { view ->
        val animator = ObjectAnimator.ofFloat(ImageView_Don, "rotationY", 0.0f, 360.0f)
        animator.duration = 600
        animator.start()
    }

    private fun setupView()
    {
        title = "ObjectAnimator"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        Button_GO.setOnClickListener(goButtonClickHandler)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean
    {
        when(item?.itemId)
        {
            android.R.id.home ->
            {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}