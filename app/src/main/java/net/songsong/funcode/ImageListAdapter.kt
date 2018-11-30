package net.songsong.funcode

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import model.ImageModel
import kotlinx.android.synthetic.main.layout_image_list_item.view.*

class ImageListAdapter(private val _feedModeltems: List<ImageModel>) : RecyclerView.Adapter<ImageListAdapter.ViewHolder>()
{
    //入口
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        //指定了Layout
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_image_list_item, parent, false)
        return ViewHolder(view)
    }

    //綁定資料
    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        holder.bindImageModel(_feedModeltems[position])
    }

    //返回數目
    override fun getItemCount(): Int
    {
        return _feedModeltems.size
    }

    //view
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        fun bindImageModel(imageModel: ImageModel)
        {
            //set description
            itemView.TextView_Description.text = imageModel._description

            //set image
            when (imageModel._imageName)
            {
                "img_1" -> itemView.ImageView_ShowPhoto.setImageResource(R.drawable.img_1)
                "img_2" -> itemView.ImageView_ShowPhoto.setImageResource(R.drawable.img_2)
                "img_3" -> itemView.ImageView_ShowPhoto.setImageResource(R.drawable.img_3)
                "img_4" -> itemView.ImageView_ShowPhoto.setImageResource(R.drawable.img_4)
                "img_5" -> itemView.ImageView_ShowPhoto.setImageResource(R.drawable.img_5)
            }
        }
    }
}