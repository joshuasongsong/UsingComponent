package net.songsong.funcode

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.PopupWindow
import kotlinx.android.synthetic.main.activity_show_view.*
import kotlinx.android.synthetic.main.layout_popupwindow.view.*

class ShowViewActivity : AppCompatActivity()
{
    private lateinit var popupWindow: PopupWindow
    private lateinit var rootView: View
    private var isFloationActionButtonOpen = false

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_view)

        title = "ShowView"
        rootView = LayoutInflater.from(this).inflate(R.layout.activity_main, null)

        setupPopupWindow()
        setupFloatingActionButton()
    }

    private fun setupPopupWindow()
    {
        //init
        popupWindow = PopupWindow(this)
    }

    private fun setupFloatingActionButton()
    {
        val floatingActionButton1: FloatingActionButton = findViewById(R.id.FloatingActionButton_LayoutMain1)
        val floatingActionButton2: FloatingActionButton = findViewById(R.id.FloatingActionButton_LayoutMain2)
        val floatingActionButton3: FloatingActionButton = findViewById(R.id.FloatingActionButton_LayoutMain3)

        //hide two button at the beginning
        floatingActionButton2.visibility = View.INVISIBLE
        floatingActionButton3.visibility = View.INVISIBLE

        //init animations
        val showLayoutAnimation = AnimationUtils.loadAnimation(this, R.anim.show_layout)
        val hideLayoutAnimation = AnimationUtils.loadAnimation(this, R.anim.hide_layout)
        val showButtonAnimation = AnimationUtils.loadAnimation(this, R.anim.show_button)
        val hideButtonAnimation = AnimationUtils.loadAnimation(this, R.anim.hide_button)

        //setup listener
        Button_LayoutMainShow.setOnClickListener(showButtonClickHandler)

        //open/close FloatingActionButton
        floatingActionButton1.setOnClickListener { v ->

            if (isFloationActionButtonOpen)
            {
                floatingActionButton1.startAnimation(hideButtonAnimation)
                floatingActionButton2.startAnimation(hideLayoutAnimation)
                floatingActionButton3.startAnimation(hideLayoutAnimation)
            }
            else
            {
                floatingActionButton1.startAnimation(showButtonAnimation)
                floatingActionButton2.startAnimation(showLayoutAnimation)
                floatingActionButton3.startAnimation(showLayoutAnimation)
            }
            isFloationActionButtonOpen = !isFloationActionButtonOpen
        }

        floatingActionButton2.setOnClickListener { v ->
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("tel:0963123456")))
        }

        floatingActionButton3.setOnClickListener { v ->
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("sms:0963123456")))
        }
    }

    private var showButtonClickHandler = View.OnClickListener { v ->
        showPopupWindow()
    }

    private fun showPopupWindow()
    {
        val popupView = LayoutInflater.from(this).inflate(R.layout.layout_popupwindow, null)

        popupView.Button_LayoutPopupwindowConfirm.setOnClickListener { v ->
            popupWindow.dismiss()
        }

        popupWindow.contentView = popupView
        popupWindow.width = ViewGroup.LayoutParams.MATCH_PARENT
        popupWindow.height = ViewGroup.LayoutParams.WRAP_CONTENT
        popupWindow.isOutsideTouchable = true
        popupWindow.showAsDropDown(Button_LayoutMainShow)
    }
}
