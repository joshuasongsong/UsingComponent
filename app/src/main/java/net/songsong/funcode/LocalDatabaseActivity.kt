package net.songsong.funcode

import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.GridLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import model.SQLItemModel
import kotlinx.android.synthetic.main.activity_local_database.*

class LocalDatabaseActivity : AppCompatActivity()
{
    lateinit var adapter: SQLItemsAdapter
    lateinit var dbHelper: MemberDatabaseHelper
    var items = ArrayList<SQLItemModel>()

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_local_database)

        dbHelper = MemberDatabaseHelper(this)

        setupView()
    }

    private fun setupView()
    {
        adapter = SQLItemsAdapter(items)

        RecyclerView_LayoutItems.layoutManager = GridLayoutManager(this, 1)
        RecyclerView_LayoutItems.adapter = adapter

        reloadData()
    }

    private fun reloadData()
    {
        items = dbHelper.getName()
        adapter.items = items
        adapter.notifyDataSetChanged()
    }

    private fun didClickPlus()
    {
        val inputAlert = AlertDialog.Builder(this)
        inputAlert.setTitle("Add Name")
        inputAlert.setMessage("Your name is: ")

        val userInput = EditText(this)
        inputAlert.setView(userInput)

        inputAlert.setPositiveButton("新增", DialogInterface.OnClickListener { dialog, which ->
            addNewName("${userInput.text.toString()}")
        })

        inputAlert.setNegativeButton("取消", DialogInterface.OnClickListener { dialog, which ->
            println("取消")
        })

        inputAlert.show()
    }

    private fun addNewName(name: String)
    {
        MemberDatabaseHelper(this).addName(name)
        reloadData()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean
    {
        this.menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean
    {
        when (item?.itemId)
        {
            R.id.layout_menu_plus ->
            {
                didClickPlus()
                return true
            }

            else -> System.err.println("item not found onOptionsItemSelected")
        }
        return super.onOptionsItemSelected(item)
    }
}
