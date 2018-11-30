package net.songsong.funcode

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import model.ProductModel
import java.util.*
import kotlinx.android.synthetic.main.activity_pull_to_request.*

class PullToRequestActivity : AppCompatActivity()
{
    var products = ArrayList<ProductModel>()

    lateinit var productsAdapter:ProductsAdapter

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pull_to_request)

        setupView()
    }

    private val productsRefreshListener = SwipeRefreshLayout.OnRefreshListener {

        Thread.sleep(200)

        val newProducts = ArrayList<ProductModel>()

        for(i in 0..10)
        {
            val randomNumber = Random().nextInt(100)
            newProducts.add(ProductModel("Product $randomNumber"))
        }
        productsAdapter.products = newProducts

        productsAdapter.notifyDataSetChanged()

        RefreshLayout_Products.isRefreshing = false
    }

    private fun setupView()
    {
        products.add(ProductModel("Product 1"))

        productsAdapter = ProductsAdapter(products)

        RecyclerView_Product.layoutManager = GridLayoutManager(this,2)

        RecyclerView_Product.adapter = productsAdapter

        RefreshLayout_Products.setProgressViewOffset(true,50,100)

        RefreshLayout_Products.setSize(SwipeRefreshLayout.LARGE)

        RefreshLayout_Products.isEnabled = true

        RefreshLayout_Products.setOnRefreshListener(productsRefreshListener)
    }
}
