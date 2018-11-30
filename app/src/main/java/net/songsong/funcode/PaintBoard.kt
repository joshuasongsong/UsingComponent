package net.songsong.funcode

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import java.io.OutputStream

class PaintBoard(context: Context, attrs: AttributeSet) : View(context, attrs)
{
    private var mPaint: Paint
    private var mBitmap: Bitmap
    private var mCanvas: Canvas

    private var mStartX: Float = 0f
    private var mStartY: Float = 0f

    init
    {
        //bitmap
        val width = Resources.getSystem().displayMetrics.widthPixels
        mBitmap = Bitmap.createBitmap(width, 800, Bitmap.Config.ARGB_8888)

        //Canvas
        mCanvas = Canvas(mBitmap)
        mCanvas.drawColor(Color.YELLOW)

        //Paint
        mPaint = Paint()
        mPaint.color = Color.BLACK
        mPaint.strokeWidth = 10f
    }

    override fun onDraw(canvas: Canvas?)
    {
        super.onDraw(canvas)

        canvas!!.drawBitmap(mBitmap, 0f, 0f, mPaint)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean
    {
        when (event!!.action)
        {
            MotionEvent.ACTION_DOWN ->
            {
                mStartX = event!!.x
                mStartY = event!!.y
            }

            MotionEvent.ACTION_MOVE ->
            {
                val stopX = event!!.x
                val stopY = event!!.y

                mCanvas.drawLine(mStartX, mStartY, stopX, stopY, mPaint)
                mStartX = event.x
                mStartY = event.y

                //call onDraw
                invalidate()
            }
        }
        return true
    }

    fun saveBitmap(stream: OutputStream)
    {
        mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
    }
}