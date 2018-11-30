package net.songsong.funcode

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

class ItemsAdapter(val items: List<ItemModel>, val layoutManager: GridLayoutManager) : RecyclerView.Adapter<ItemsAdapter.ViewHolder>()
{
    val VIEW_TYPE_SMALL = 1
    val VIEW_TYPE_BIG = 2

    override fun getItemViewType(position: Int): Int
    {
        val spanCount = layoutManager.spanCount

        when (spanCount)
        {
            2 -> return VIEW_TYPE_SMALL
            else -> return VIEW_TYPE_BIG
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        val metrics = parent.context.resources.displayMetrics

        when (viewType)
        {
            VIEW_TYPE_SMALL ->
            {
                println("create view holder small")
                val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_item_small, parent, false)

                view.minimumWidth = 900 * (1080 / metrics.widthPixels)
                view.minimumHeight = 220
                return ViewHolder(view,viewType)
            }

            else ->
            {
                println("create view holder big")
                val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_item_big,parent,false)

                view.minimumWidth = metrics.widthPixels
                view.minimumHeight = 220
                return ViewHolder(view,viewType)
            }
        }

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        val item = items[position]
        holder.bindModel(item)
    }

    override fun getItemCount(): Int
    {
        return items.size
    }

    inner class ViewHolder(itemView: View, var viewType: Int) : RecyclerView.ViewHolder(itemView)
    {
        var imageView: ImageView? = null
        var nameTextView: TextView? = null
        var likeTextView: TextView? = null
        var commentTextView: TextView? = null

        fun bindModel(item: ItemModel)
        {
            when (viewType)
            {
                VIEW_TYPE_SMALL ->
                {
                    imageView = itemView.findViewById(R.id.ImageView_Small)
                    nameTextView = itemView.findViewById(R.id.TextView_SmallName)
                }

                else ->
                {
                    imageView = itemView.findViewById(R.id.ImageView_BigName)
                    nameTextView = itemView.findViewById(R.id.TextView_BigName)
                    likeTextView = itemView.findViewById(R.id.TextView_Like)
                    commentTextView = itemView.findViewById(R.id.TextView_Comment)
                }
            }
            imageView?.setImageResource(item.image)
            nameTextView?.text = item.name
            likeTextView?.text = "Likes:${item.likeCount}"
            commentTextView?.text = "comments:${item.commentCount}"
        }
    }
}