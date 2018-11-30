package net.songsong.funcode

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.layout_product_item.view.*
import model.ProductModel

class ProductsAdapter(var products: ArrayList<ProductModel>) : RecyclerView.Adapter<ProductsAdapter.ViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_product_item, parent, false)

        val metrics = parent.context.resources.displayMetrics

        Log.e("log", "${metrics.widthPixels}")
        view.minimumWidth = 525 * (1080 / metrics.widthPixels)
        view.minimumWidth = 150

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        holder.bind(position, products[position])
    }

    override fun getItemCount(): Int
    {
        return products.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        fun bind(position: Int, product: ProductModel)
        {
            itemView.TextView_Name.text = product.name
        }
    }
}