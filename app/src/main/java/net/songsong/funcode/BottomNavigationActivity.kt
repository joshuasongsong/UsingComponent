package net.songsong.funcode

import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_bottom_navigation.*

class BottomNavigationActivity : AppCompatActivity(),DashboardFragment.OnFragmentInteractionListener, HomeFragment.OnFragmentInteractionListener, NotificationDashboardFragment.OnFragmentInteractionListener
{
    val manager = supportFragmentManager

    enum class FragmentType
    {
        home, dashboard, notification
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_navigation)

        title = "Home"

        initView()
    }

    private fun initView()
    {
        BottomNavigationView_Navigation.setOnNavigationItemSelectedListener { item ->
            when (item?.itemId)
            {
                R.id.Navigation_Home ->
                {
                    changeFragmentTo(FragmentType.home)
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.Navigation_Dashboard ->
                {
                    title = "Dashboard"
                    changeFragmentTo(FragmentType.dashboard)
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.Navigation_Notifications ->
                {
                    title = "Notification"
                    changeFragmentTo(FragmentType.notification)
                    return@setOnNavigationItemSelectedListener true
                }
            }
            return@setOnNavigationItemSelectedListener false
        }
        changeFragmentTo(FragmentType.home)
    }

    private fun changeFragmentTo(_type: FragmentType)
    {
        val transaction = manager.beginTransaction()

        when (_type)
        {
            FragmentType.home ->
            {
                title = "Home"
                val homeFragment = HomeFragment()
                transaction.replace(R.id.FrameLayout_BaseFragment, homeFragment)
            }

            FragmentType.dashboard ->
            {
                val dashboardFragment = DashboardFragment()
                transaction.replace(R.id.FrameLayout_BaseFragment, dashboardFragment)
            }

            FragmentType.notification ->
            {
                val notificationFragment = NotificationDashboardFragment()
                transaction.replace(R.id.FrameLayout_BaseFragment, notificationFragment)
            }
        }
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onFragmentInteraction(uri: Uri)
    {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}