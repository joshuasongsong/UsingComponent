package net.songsong.funcode

import android.app.Service
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.*
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_shake_shake.*

class ShakeShakeActivity : AppCompatActivity()
{

    private lateinit var vibrator: Vibrator
    private lateinit var sensorManager: SensorManager

    private var images = intArrayOf(R.drawable.img_view_1, R.drawable.img_view_2, R.drawable.img_view_3, R.drawable.img_view_4, R.drawable.img_view_5)
    private var currentImageNumber = 0
    private var isChangingPhoto = false

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shake_shake)
    }

    override fun onDestroy()
    {
        super.onDestroy()

        sensorManager.unregisterListener(sensorListener)
    }

    private fun setupView()
    {
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        val sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        sensorManager.registerListener(sensorListener, sensor, SensorManager.SENSOR_DELAY_NORMAL)

        printAllSensors()

        vibrator = getSystemService(Service.VIBRATOR_SERVICE) as Vibrator
    }

    private val sensorListener = object : SensorEventListener
    {

        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int)
        {
        }

        override fun onSensorChanged(event: SensorEvent?)
        {
            if (event != null)
            {
                val xValue = Math.abs(event.values[0]) //加速度-X軸方向
                val yValue = Math.abs(event.values[1]) //加速度-y軸方向
                val zValue = Math.abs(event.values[2]) //加速度-z軸方向

                if (xValue > 20 || yValue > 20 || zValue > 20)
                {
                    shakeHandler()
                }
            }
        }
    }

    private fun shakeHandler()
    {
        if (isChangingPhoto)
        {
            return
        }

        isChangingPhoto = true

        changeImage()

        doVibrate()

        //防止在搖動手機過程連續換圖和震動
        Handler().postDelayed(
            {
                isChangingPhoto = false
            }, 100
        )
    }

    private fun doVibrate()
    {
        if (Build.VERSION.SDK_INT >= 26)
        {
            vibrator.vibrate(VibrationEffect.createOneShot(100, 10))
        }
        else if (Build.VERSION.SDK_INT < 26)
        {
            vibrator.vibrate(100)
        }
    }

    private fun changeImage()
    {
        when (currentImageNumber)
        {
            0 -> ImageView_LayoutLand.setImageResource(images[1])
            1 -> ImageView_LayoutLand.setImageResource(images[2])
            2 -> ImageView_LayoutLand.setImageResource(images[3])
            3 -> ImageView_LayoutLand.setImageResource(images[4])
            4 -> ImageView_LayoutLand.setImageResource(images[5])
        }
        if (currentImageNumber != 4)
        {
            currentImageNumber += 1
        }
        else
        {
            currentImageNumber = 0
        }
    }

    private fun printAllSensors()
    {
        val allSensors = sensorManager.getSensorList(Sensor.TYPE_ALL)

        for (sensor in allSensors)
        {
            Log.i("sensors", "${sensor.name} - ${sensor.vendor} - ${sensor.version}")
        }
    }
}
