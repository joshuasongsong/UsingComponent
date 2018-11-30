package net.songsong.funcode

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_face_recognizer.*

class FaceRecognizerActivity : AppCompatActivity()
{
    private val OPEN_PHOTO_FOLDER_REQUEST_CODE = 1004

    private lateinit var mFaceView: FaceView

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_face_recognizer)

        setupView()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
    {
        super.onActivityResult(requestCode, resultCode, data)

        println("onActivityResult requestCode is $requestCode")

        when (requestCode)
        {
            OPEN_PHOTO_FOLDER_REQUEST_CODE ->
            {
                val imageUri = data?.data
                println("imageUri is $imageUri")

                if (imageUri != null)
                {
                    mFaceView.setupWithImage(imageUri)
                }
                else
                {
                    Toast.makeText(this, "get image Uri failed", Toast.LENGTH_SHORT).show()
                }
            }
            else ->
            {
                println("no handler on ActivityResult,resultcode is $resultCode")
            }
        }
    }

    private fun setupView()
    {
        Button_LayoutMainAlbum.setOnClickListener(albumButtonClickHandler)
        Button_LayoutMainDetect.setOnClickListener(detectButtonClickHandler)

        mFaceView = findViewById(R.id.FaceView_Layout)
    }

    private var albumButtonClickHandler = View.OnClickListener { v ->
        takeImageFromAlbum()
    }

    private var detectButtonClickHandler = View.OnClickListener {
        mFaceView.detectFace()
    }

    private fun takeImageFromAlbum()
    {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT

        startActivityForResult(intent, OPEN_PHOTO_FOLDER_REQUEST_CODE)
    }
}
