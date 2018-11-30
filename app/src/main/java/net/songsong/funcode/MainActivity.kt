package net.songsong.funcode

import android.content.Intent
import android.os.*
import android.support.v7.app.AppCompatActivity
import android.view.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun btnGoActivity(view: View)
    {
        val intent: Intent = when (view.id)
        {
            R.id.btnGoTapCounter ->
            {
                Intent(this, TapCounterActivity::class.java)
            }

            R.id.btnGoSeekBarPercentage ->
            {
                Intent(this, SeekBarPercentageActivity::class.java)
            }

            R.id.btnGoImagePicker ->
            {
                Intent(this,ImagePickerActivity::class.java)
            }

            R.id.btnGoWebSearch ->
            {
                Intent(this,WebSearchActivity::class.java)
            }

            R.id.btnGoImageList ->
            {
                Intent(this,ImageListActivity::class.java)
            }

            R.id.btnGoBottomNavigation ->
            {
                Intent(this,BottomNavigationActivity::class.java)
            }

            R.id.btnGoImageSlider ->
            {
                Intent(this,ImageSliderActivity::class.java)
            }

            R.id.btnGoProgressControl ->
            {
                Intent(this,ProgressControlActivity::class.java)
            }

            R.id.btnGoActivitySchedule ->
            {
                Intent(this,ActivityScheduleActivity::class.java)
            }

            R.id.btnGoGtihubStars ->
            {
                Intent(this,GtihubStarsActivity::class.java)
            }

            R.id.btnGoLocalStorage ->
            {
                Intent(this,LocalStorageActivity::class.java)
            }

            R.id.btnGoBallAnimation ->
            {
                Intent(this,BallAnimationActivity::class.java)
            }

            R.id.btnGoNotification ->
            {
                Intent(this,NotificationActivity::class.java)
            }

            R.id.btnGoPullToRequest ->
            {
                Intent(this,PullToRequestActivity::class.java)
            }

            R.id.btnGoDetail ->
            {
                Intent(this,DetailActivity::class.java)
            }

            R.id.btnGoSideMenu ->
            {
                Intent(this,SideMenuActivity::class.java)
            }

            R.id.btnGoPlayer ->
            {
                Intent(this,PlayerActivity::class.java)
            }

            R.id.btnGoRecord ->
            {
                Intent(this,RecordActivity::class.java)
            }

            R.id.btnGoLittleBirdSound ->
            {
                Intent(this,LittleBirdSoundActivity::class.java)
            }

            R.id.btnGoNativeDetail ->
            {
                Intent(this,NativeDetailActivity::class.java)
            }

            R.id.btnGoActivityTransition ->
            {
                Intent(this,ActivityTransitionActivity::class.java)
            }

            R.id.btnGoLayoutSwitch ->
            {
                Intent(this,LayoutSwitchActivity::class.java)
            }

            R.id.btnGoLocalDatabase ->
            {
                Intent(this,LocalDatabaseActivity::class.java)
            }

            R.id.btnGoGoogleVoice ->
            {
                Intent(this,GoogleVoiceActivity::class.java)
            }

            R.id.btnGoPainter ->
            {
                Intent(this,PainterActivity::class.java)
            }

            R.id.btnGoFaceRecognizer ->
            {
                Intent(this,FaceRecognizerActivity::class.java)
            }

            R.id.btnGoActionReceive ->
            {
                Intent(this,ActionReceiveActivity::class.java)
            }

            R.id.btnGoReceiver ->
            {
                Intent(this,ReceiverActivity::class.java)
            }

            R.id.btnGoActionSend ->
            {
                Intent(this,ActionSendActivity::class.java)
            }

            R.id.btnGoShakeShake ->
            {
                Intent(this,ShakeShakeActivity::class.java)
            }

            R.id.btnGoParks ->
            {
                Intent(this,ParksActivity::class.java)
            }

            R.id.btnGoShowView ->
            {
                Intent(this,ShowViewActivity::class.java)
            }

            R.id.btnGoVideoPlayer ->
            {
                Intent(this,VideoPlayerActivity::class.java)
            }
            else ->
            {
                Intent(this, null)
            }
        }

        startActivity(intent)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean
    {
        when (event?.actionMasked)
        {
            MotionEvent.ACTION_DOWN ->
            {
                println("action down")
            }
            MotionEvent.ACTION_POINTER_DOWN ->
            {
                println("pointer down")
            }
            MotionEvent.ACTION_MOVE ->
            {
                println("action move")
            }
            MotionEvent.ACTION_POINTER_UP ->
            {
                println("pointer up")
            }
            MotionEvent.ACTION_UP ->
            {
                println("action up")
            }
        }
        return super.onTouchEvent(event)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean
    {
        this.menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean
    {
        when (item?.itemId)
        {
            R.id.reset_ButtonCount ->
            {
                System.err.println("${item.itemId}")
            }

            else -> System.err.println("item not found onOptionsItemSelected")
        }
        return super.onOptionsItemSelected(item)
    }
}
