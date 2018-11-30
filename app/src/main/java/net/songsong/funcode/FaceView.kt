package net.songsong.funcode

import android.content.ContentResolver
import android.content.Context
import android.content.res.Resources
import android.graphics.*
import android.media.FaceDetector
import android.media.ThumbnailUtils
import android.net.Uri
import android.provider.MediaStore
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.Toast

class FaceView(context: Context, attrs: AttributeSet) : View(context, attrs)
{
    private var mPaint: Paint
    private var mBitmap: Bitmap
    private var mCanvas: Canvas

    private val maxFaceNumbers = 25
    private var numberOfFaceDetected = 0
    private var faces = arrayOfNulls<FaceDetector.Face>(25)

    init
    {
        //bitmap
        val width = Resources.getSystem().displayMetrics.widthPixels
        mBitmap = Bitmap.createBitmap(width, 800, Bitmap.Config.RGB_565)

        //Canvas
        mCanvas = Canvas(mBitmap)
        mCanvas.drawColor(Color.GRAY)

        //Paint
        mPaint = Paint()
        mPaint.color = Color.YELLOW
        mPaint.style = Paint.Style.STROKE
        mPaint.strokeWidth = 4f
        mPaint.textSize = 50f
    }

    override fun onDraw(canvas: Canvas?)
    {
        super.onDraw(canvas)

        canvas!!.drawBitmap(mBitmap, 0f, 0f, null)

        for (i in 0..numberOfFaceDetected)
        {
            val face = faces[i]

            if (face != null)
            {
                val point = PointF()
                face.getMidPoint(point)

                val eyesDistance = face.eyesDistance()

                canvas.drawRect(
                    (point.x - eyesDistance).toFloat(),
                    (point.y - eyesDistance / 2).toFloat(),
                    (point.x + eyesDistance).toFloat(),
                    (point.y + eyesDistance * 3 / 2).toFloat(),
                    mPaint
                )
            }
        }
    }

    fun setupWithImage(uri: Uri)
    {
        Log.e("FaceView", "Setup With Uri $uri")

        try
        {
            val resolver = context.contentResolver

            val options = BitmapFactory.Options()
            options.inPreferredConfig = Bitmap.Config.RGB_565

            val image = BitmapFactory.decodeStream(resolver.openInputStream(uri), null, options)

            mBitmap = ThumbnailUtils.extractThumbnail(image, width, height)

        }
        catch (e: Exception)
        {
            Log.e("setup with image", "failed")
        }
    }

    fun detectFace()
    {
        //init FaceDetector
        val faceDetector = FaceDetector(mBitmap.width, mBitmap.height, maxFaceNumbers)

        faces = arrayOfNulls<FaceDetector.Face>(maxFaceNumbers)

        numberOfFaceDetected = faceDetector.findFaces(mBitmap, faces)

        Toast.makeText(context, "Detected $numberOfFaceDetected faces", Toast.LENGTH_SHORT).show()

        invalidate()
    }

}