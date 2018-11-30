package net.songsong.funcode

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.support.v4.content.FileProvider
import android.view.View
import android.widget.Toast
import java.io.File
import java.io.FileOutputStream
import kotlinx.android.synthetic.main.activity_painter.*

class PainterActivity : AppCompatActivity()
{

    private lateinit var mPaintBoard: PaintBoard

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_painter)

        setupView()
    }

    private fun setupView()
    {
        mPaintBoard = findViewById(R.id.PaintBoard_Layout)
        Button_Save.setOnClickListener(saveClickHandler)
    }

    private fun checkWritable(): Boolean
    {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE), 0)
            return false
        }
        else
        {
            return true
        }
    }

    private val saveClickHandler = View.OnClickListener { v ->

        if (checkWritable())
        {
            try
            {
                val fileName = (System.currentTimeMillis() / 1000).toString() + ".jpg"

                val file = File(Environment.getExternalStorageDirectory(), fileName)

                val stream = FileOutputStream(file)
                mPaintBoard.saveBitmap(stream)
                stream.close()

                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                val photoUri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".provider", file)

                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
                intent.action = Intent.ACTION_MEDIA_SCANNER_SCAN_FILE
                intent.data = Uri.fromFile(Environment.getExternalStorageDirectory())

                sendBroadcast(intent)

                Toast.makeText(this, "Save Success", Toast.LENGTH_SHORT).show()
            }
            catch (e: Exception)
            {
                println(e)
                Toast.makeText(this, "Save Failed", Toast.LENGTH_SHORT).show()
            }
        }
    }

}
