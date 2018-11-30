package animations

import android.animation.ObjectAnimator
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.animation.*
import kotlinx.android.synthetic.main.activity_animation4.*
import net.songsong.funcode.R

class Animation4Activity : AppCompatActivity()
{
    private lateinit var animator:ObjectAnimator

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animation4)

        setupView()
    }

    private fun setupView()
    {
        title = "AnimatorSet"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        animator = ObjectAnimator.ofFloat(ImageView_Don,"translationX",0f,600f,0f)
        animator.duration = 1500

        Button_Accelerate.setOnClickListener(buttonAccelerateClickHandler)
        Button_AccelerateDecelerate.setOnClickListener(buttonAccelerateDecelerateClickHandler)
        Button_Anticipate.setOnClickListener(buttonAnticipateClickHandler)
        Button_AnticipateOvershoot.setOnClickListener(buttonAnticipateOvershootClickHandler)
        Button_Bounce.setOnClickListener(buttonBounceClickHandler)
        Button_Cycle.setOnClickListener(buttonCycleClickHandler)
        Button_Decelerate.setOnClickListener(buttonDecelerateClickHandler)
        Button_Linear.setOnClickListener(buttonLinearClickHandler)
        Button_Overshoot.setOnClickListener(buttonOvershootClickHandler)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.getItemId())
        {
            android.R.id.home ->
            {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private val buttonAccelerateDecelerateClickHandler = View.OnClickListener { view ->
        animator.interpolator = AccelerateDecelerateInterpolator()
        animator.start()
    }

    private val buttonAnticipateOvershootClickHandler = View.OnClickListener { view ->
        animator.interpolator = AnticipateInterpolator()
        animator.start()
    }

    private val buttonBounceClickHandler = View.OnClickListener { view ->
        animator.interpolator = BounceInterpolator()
        animator.start()
    }

    private val buttonOvershootClickHandler = View.OnClickListener { view ->
        animator.interpolator = OvershootInterpolator()
        animator.start()
    }

    private val buttonAccelerateClickHandler = View.OnClickListener { view ->
        animator.interpolator = AccelerateInterpolator()
        animator.start()
    }

    private val buttonLinearClickHandler = View.OnClickListener { view ->
        animator.interpolator = LinearInterpolator()
        animator.start()
    }

    private val buttonAnticipateClickHandler = View.OnClickListener { view ->
        animator.interpolator = AnticipateInterpolator()
        animator.start()
    }

    private val buttonDecelerateClickHandler = View.OnClickListener { view ->
        animator.interpolator = DecelerateInterpolator()
        animator.start()
    }

    private val buttonCycleClickHandler = View.OnClickListener { view ->
        animator.interpolator = CycleInterpolator(2.0f)
        animator.start()
    }
}
