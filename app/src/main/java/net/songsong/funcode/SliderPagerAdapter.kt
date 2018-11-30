package net.songsong.funcode

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_slider.view.*

class SliderPagerAdapter : PagerAdapter
{
    val context: Context
    val images: IntArray
    lateinit var inflator: LayoutInflater

    constructor(_context: Context, _images: IntArray) : super()
    {
        this.context = _context
        this.images = _images
    }

    // 來判斷顯示的是否是同一張圖片，這裡我們將兩個參數相比較返回即可
    override fun isViewFromObject(view: View, `object`: Any): Boolean
    {
        return view == `object` as ConstraintLayout
    }

    // 獲取要滑動的控件的數量，在這裡我們以滑動的廣告欄為例，那麼這裡就應該是展示的廣告圖片的ImageView數量
    override fun getCount(): Int
    {
        return images.size
    }

    // 當要顯示的圖片可以進行緩存的時候，會調用這個方法進行顯示圖片的初始化，我們將要顯示的ImageView加入到ViewGroup中，然後作為返回值返回即可
    override fun instantiateItem(container: ViewGroup, position: Int): Any
    {
        inflator = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val rv: View = inflator.inflate(R.layout.fragment_slider, container, false)
        rv.ImageView_ShowPhoto.setImageResource(images[position])
        container!!.addView(rv)

        return rv
    }

    // PagerAdapter只緩存三張要顯示的圖片，如果滑動的圖片超出了緩存的範圍，就會調用這個方法，將圖片銷毀
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any)
    {
        container!!.removeView(`object` as ConstraintLayout)
    }
}