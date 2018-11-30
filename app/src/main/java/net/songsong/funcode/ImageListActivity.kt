package net.songsong.funcode

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import model.ImageModel
import kotlinx.android.synthetic.main.activity_image_list.*

class ImageListActivity : AppCompatActivity()
{
    private val imageList by lazy {
        listOf(
            ImageModel("img_1", "Photo1"),
            ImageModel("img_2", "Photo2"),
            ImageModel("img_3", "Photo3"),
            ImageModel("img_4", "Photo4"),
            ImageModel("img_5", "Photo5")
        )
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_list)

        title="Image List"

        RecyclerView_ImageList.layoutManager = LinearLayoutManager(this)

        val adapter = ImageListAdapter(imageList)
        RecyclerView_ImageList.adapter = adapter
    }
}
