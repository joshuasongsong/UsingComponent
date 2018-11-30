package net.songsong.funcode

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.webkit.WebViewClient
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_web_search.*

class WebSearchActivity : AppCompatActivity()
{
    private val webViewClient = WebViewClient()

    private val searchButtonHandler = View.OnClickListener { view ->
        val searchText = EditText_Search.text.toString()
        search(searchText)

        val ime = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        ime.hideSoftInputFromWindow(view.windowToken,0)
    }

    private val searchEditTextHandler = TextView.OnEditorActionListener { v, actionId, event ->
        println("did enter EditTextHandler")

        if(actionId == EditorInfo.IME_ACTION_SEARCH)
        {
            val searchText = EditText_Search.text.toString()
            search(searchText)
        }

        val ime = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        ime.hideSoftInputFromWindow(v.windowToken,0)
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_search)

        WebView.webViewClient = webViewClient
        WebView.loadUrl("https://www.google.com")

        //set handler
        Button_Search.setOnClickListener(searchButtonHandler)
        EditText_Search.setOnEditorActionListener(searchEditTextHandler)
    }

    private fun search(_text:String)
    {
        if(_text.isEmpty())
        {
            val toast = Toast.makeText(this,"請輸入內容", Toast.LENGTH_LONG)
            toast.show()
        }
        else
        {
            WebView.loadUrl("https://www.google.com/search?q=$_text")
        }
    }
}
