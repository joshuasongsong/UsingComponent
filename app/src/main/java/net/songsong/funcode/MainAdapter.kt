package net.songsong.funcode

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import kotlinx.android.synthetic.main.item_main.view.*
import java.util.*

class MainAdapter(val clickListener: (position: Int) -> Unit) : RecyclerView.Adapter<MainAdapter.ViewHolder>()
{
    val items: ArrayList<String> = arrayListOf("ValueAnimator", "ObjectAnimator", "AnimatorSet", "Interpolator")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_main, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        holder.itemView.setOnClickListener { clickListener(position) }
        holder.bindInformation(position, items[position])
    }

    override fun getItemCount(): Int
    {
        return items.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        fun bindInformation(position: Int, description: String)
        {
            itemView.TextView_Position.text = "$position"
            itemView.TextView_Description.text = description
        }
    }


}