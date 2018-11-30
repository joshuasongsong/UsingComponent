package net.songsong.funcode

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Color
import android.media.RingtoneManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.NotificationCompat
import android.view.View
import kotlinx.android.synthetic.main.activity_notification.*

class NotificationActivity : AppCompatActivity()
{

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        setupView()
    }

    private val sendButtonClickHandler = View.OnClickListener { v ->

        val notification = NotificationCompat.Builder(this,"test")
                .setSmallIcon(R.drawable.star_big_on)
                .setLargeIcon(BitmapFactory.decodeResource(resources,R.drawable.icon_don))
                .setContentTitle("Notification from Don")
                .setContentText("it's time to go")
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setVibrate(longArrayOf(300,600,300,600))
                .setLights(Color.RED,1000,1000)
                .setChannelId("1")

        if (Build.VERSION.SDK_INT >= 26)
        {
            val nChannel = NotificationChannel("1", "test", NotificationManager.IMPORTANCE_DEFAULT)

            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            notificationManager.createNotificationChannel(nChannel)

            notificationManager.notify(1,notification.build())
        }
    }

    fun setupView()
    {
        Button_SendMessage.setOnClickListener(sendButtonClickHandler)
    }
}
