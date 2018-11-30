package net.songsong.funcode

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_tap_counter.*

class TapCounterActivity : AppCompatActivity()
{
    private var intNumberCount: Int = 0

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tap_counter)
    }

    private fun initView()
    {
        Button_ClickAddCount.setOnClickListener {
            intNumberCount += 1
            TextView_ShowCount.text = intNumberCount.toString()
        }
    }

    fun clickAddCount(view: View)
    {
        intNumberCount += 1
        TextView_ShowCount.text = intNumberCount.toString()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean
    {
        this.menuInflater.inflate(R.menu.menu_main,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean
    {
        when(item?.itemId)
        {
            R.id.reset_ButtonCount->
            {
                this.intNumberCount = 0
                this.TextView_ShowCount.text = "0"
            }
            else-> println("item not found onOptionsItemSelected")
        }

        return super.onOptionsItemSelected(item)
    }
}
