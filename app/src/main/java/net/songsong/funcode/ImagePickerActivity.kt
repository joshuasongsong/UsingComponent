package net.songsong.funcode

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_image_picker.*

class ImagePickerActivity : AppCompatActivity()
{
    private val intACTION_CAMERA_REQUEST_CODE = 100
    private val intACTION_ALBUM_REQUEST_CODE = 200

    private val cameraAppButtonHandler = View.OnClickListener { view->
        takeImageFromCameraWithIntent()
    }

    private val albumAppButtonHandler = View.OnClickListener { view->
        takeImageFromAlbumWithIntent()
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_picker)

        Button_CameraAppButton.setOnClickListener(cameraAppButtonHandler)
        Button_AlbumAppButton.setOnClickListener(albumAppButtonHandler)

        ImageView_ShowPhoto.scaleType = ImageView.ScaleType.CENTER_CROP
    }

    //通過intent使用camera
    private fun takeImageFromCameraWithIntent()
    {
        println("take image from camera")

        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent,intACTION_CAMERA_REQUEST_CODE)
    }

    //通過intent使用album
    private fun takeImageFromAlbumWithIntent()
    {
        println("take image from album")

        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent,intACTION_ALBUM_REQUEST_CODE)
    }

    private fun displayImage(_bitmap: Bitmap)
    {
        ImageView_ShowPhoto.setImageBitmap(_bitmap)
    }

    override fun onActivityResult(_requestCode: Int, _resultCode: Int, _data: Intent?)
    {
        super.onActivityResult(_requestCode, _resultCode, _data)
        println("收到result code $_requestCode")

        when(_requestCode)
        {
            intACTION_CAMERA_REQUEST_CODE->
            {
                if(_resultCode == Activity.RESULT_OK && _data != null)
                {
                    displayImage(_data.extras.get("data") as Bitmap)
                }
            }
            intACTION_ALBUM_REQUEST_CODE->{
                if(_resultCode == Activity.RESULT_OK && _data != null)
                {
                    val resolver = this.contentResolver
                    val bitmap = MediaStore.Images.Media.getBitmap(resolver,_data?.data)
                    displayImage(bitmap)
                }
            }
            else ->
            {
                println("no handler onActivityReenter")
            }
        }
    }
}
