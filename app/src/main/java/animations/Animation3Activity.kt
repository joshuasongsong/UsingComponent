package animations

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_animation3.*
import net.songsong.funcode.R

class Animation3Activity : AppCompatActivity()
{

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animation3)

        setupView()
    }

    private fun setupView()
    {
        title = "AnimatorSet"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        Button_OneByOne.setOnClickListener(buttonOnebyOneClickHandler)
        Button_SameTime.setOnClickListener(buttonSameTimeClickHandler)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean
    {
        when (item.getItemId()) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private val buttonOnebyOneClickHandler = View.OnClickListener { view ->
        val animator1 = ObjectAnimator.ofFloat(ImageView_Don1,"translationY",0f, -800f, 0f)
        val animator2 = ObjectAnimator.ofFloat(ImageView_Don2,"translationY",0f, 800f, 0f)

        val animatorSet = AnimatorSet()
        animatorSet.duration = 600
        animatorSet.play(animator1).after(animator2)
        animatorSet.start()
    }

    private val buttonSameTimeClickHandler = View.OnClickListener { view ->
        val animator1 = ObjectAnimator.ofFloat(ImageView_Don1,"translationY",0f, -800f, 0f)
        val animator2 = ObjectAnimator.ofFloat(ImageView_Don2,"translationY",0f, 800f, 0f)

        val animatorSet = AnimatorSet()
        animatorSet.duration = 600
        animatorSet.playTogether(animator1, animator2)
        animatorSet.start()
    }
}
