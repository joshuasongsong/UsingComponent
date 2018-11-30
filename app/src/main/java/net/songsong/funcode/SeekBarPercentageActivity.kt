package net.songsong.funcode

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView

class SeekBarPercentageActivity : AppCompatActivity()
{
    private var value: Float = 0f

    private val etxtKeyPrice: EditText by lazy { findViewById<EditText>(R.id.EditText_KeyPrice)}
    private val txtvDiscountPrice: TextView by lazy { findViewById<TextView>(R.id.TextView_DiscountPriceText) }
    private val txtvDiscount: TextView by lazy { findViewById<TextView>(R.id.TextView_DiscountText) }
    private val txtvPercent: TextView by lazy { findViewById<TextView>(R.id.TextView_PercentText) }
    private val txtvDescription: TextView by lazy { findViewById<TextView>(R.id.TextView_Description) }
    private val txtvResult: TextView by lazy { findViewById<TextView>(R.id.TextView_ResultText) }
    private val sbProgressBar: SeekBar by lazy { findViewById<SeekBar>(R.id.SeekBar_ProgressBar) }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seek_bar_percentage)

        initView()
    }

    private fun initView()
    {
        sbProgressBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(_seekBar: SeekBar?, _progress: Int, _fromUser: Boolean)
            {
                value = etxtKeyPrice.text.toString().removePrefix("$").toFloatOrNull() ?:0f
                txtvPercent.text = "打折($_progress%)"
                calculateResult()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?)
            {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?)
            {
            }
        })

        etxtKeyPrice.setOnEditorActionListener { textview, actionId, keyEvent ->
            if(actionId == EditorInfo.IME_ACTION_DONE)
            {
                value = etxtKeyPrice.text.toString().removePrefix("$").toFloatOrNull() ?:0f
                etxtKeyPrice.setText("$$value")
                calculateResult()
            }

            //false表示收起鍵盤(不保留鍵盤)
            etxtKeyPrice.clearFocus()
            false
        }
    }

    private fun calculateResult()
    {
        val discount = sbProgressBar.progress * value /100
        val total = value - discount
        txtvDiscount.text = String.format("%.2f",discount)
        txtvResult.text = String.format("%.2f",total)
    }
}
