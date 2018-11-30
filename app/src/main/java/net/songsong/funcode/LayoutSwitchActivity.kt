package net.songsong.funcode

import android.annotation.TargetApi
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import kotlinx.android.synthetic.main.activity_layout_switch.*

class LayoutSwitchActivity : AppCompatActivity()
{
    lateinit var gridLayoutManager: GridLayoutManager
    lateinit var itemsAdapter: ItemsAdapter
    lateinit var items: ArrayList<ItemModel>

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout_switch)

        setupItems()

        gridLayoutManager = GridLayoutManager(this, 2)

        itemsAdapter = ItemsAdapter(items, gridLayoutManager)

        RecyclerView_Items.adapter = itemsAdapter
        RecyclerView_Items.layoutManager = gridLayoutManager
    }

    private fun setupItems()
    {
        items = ArrayList<ItemModel>()

        items.add(ItemModel("Mountain Image 1", 10, 200, R.drawable.img_mountain_1))
        items.add(ItemModel("Mountain Image 2", 20, 220, R.drawable.img_mountain_2))
        items.add(ItemModel("Mountain Image 3", 30, 230, R.drawable.img_mountain_3))
        items.add(ItemModel("Mountain Image 4", 40, 240, R.drawable.img_mountain_4))
        items.add(ItemModel("Mountain Image 5", 50, 450, R.drawable.img_mountain_5))
        items.add(ItemModel("Mountain Image 6", 60, 520, R.drawable.img_mountain_6))
        items.add(ItemModel("Mountain Image 7", 70, 260, R.drawable.img_mountain_7))
        items.add(ItemModel("Mountain Image 8", 80, 820, R.drawable.img_mountain_8))
        items.add(ItemModel("Mountain Image 9", 10, 20, R.drawable.img_mountain_9))
        items.add(ItemModel("Mountain Image 10", 110, 320, R.drawable.img_mountain_10))

        items.add(ItemModel("Mountain Image 11", 120, 420, R.drawable.img_mountain_11))
        items.add(ItemModel("Mountain Image 12", 430, 520, R.drawable.img_mountain_12))
        items.add(ItemModel("Mountain Image 13", 540, 620, R.drawable.img_mountain_13))
        items.add(ItemModel("Mountain Image 14", 350, 720, R.drawable.img_mountain_14))
        items.add(ItemModel("Mountain Image 15", 260, 820, R.drawable.img_mountain_15))
        items.add(ItemModel("Mountain Image 16", 170, 220, R.drawable.img_mountain_16))
        items.add(ItemModel("Mountain Image 17", 180, 320, R.drawable.img_mountain_17))
        items.add(ItemModel("Mountain Image 18", 190, 420, R.drawable.img_mountain_18))
        items.add(ItemModel("Mountain Image 19", 140, 620, R.drawable.img_mountain_19))
        items.add(ItemModel("Mountain Image 20", 130, 320, R.drawable.img_mountain_20))
    }

    // 切換 Layout 並重新 render 畫面
    private fun switchLayout()
    {
        if (gridLayoutManager.spanCount == 1)
        {
            gridLayoutManager.spanCount = 2
        }
        else
        {
            gridLayoutManager.spanCount = 1
        }
        itemsAdapter.notifyItemRangeChanged(0, itemsAdapter.itemCount)
    }

    // 切換Switch圖標
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private fun switchIcon(item: MenuItem)
    {
        if (gridLayoutManager.spanCount == 2)
        {
            item.icon = resources.getDrawable(R.drawable.icon_menu_1, theme)
        }
        else
        {
            item.icon = resources.getDrawable(R.drawable.icon_menu_2, theme)
        }
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
            R.id.reset_ButtonCount ->
            {
                System.err.println("${item.itemId}")
            }

            R.id.Menu_Switch_Layout ->
            {
                switchLayout()
                switchIcon(item)
                return true
            }

            else -> System.err.println("item not found onOptionsItemSelected")
        }
        return super.onOptionsItemSelected(item)
    }
}
