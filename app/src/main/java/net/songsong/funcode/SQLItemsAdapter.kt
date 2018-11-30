package net.songsong.funcode

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.layout_item.view.*
import model.SQLItemModel

class SQLItemsAdapter(var items: ArrayList<SQLItemModel>) : RecyclerView.Adapter<SQLItemsAdapter.ViewHolder>()
{
    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        holder.bindModel(items[position], position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int
    {
        return items.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        fun bindModel(item: SQLItemModel, position: Int)
        {
            itemView.TextView_Layout_Item_Number.text = "$position"
            itemView.TextView_Layout_Item_Name.text = item.name
        }
    }
}