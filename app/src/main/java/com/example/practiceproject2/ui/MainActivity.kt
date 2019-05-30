package com.example.practiceproject2.ui

import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import com.example.practiceproject2.R
import com.example.practiceproject2.base.BaseActivity
import com.example.practiceproject2.ui.home.HomeFragment
import com.example.practiceproject2.ui.notification.NotificationFragment
import com.example.practiceproject2.ui.profile.ProfileFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun getViewId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        setupMainTab()
        openHomeFragment()
    }

    private fun setupMainTab() {
        //Add customs view
        mainTabs.addTab(mainTabs.newTab().setCustomView(R.layout.tab_home))
        mainTabs.addTab(mainTabs.newTab().setCustomView(R.layout.tab_notification))
        mainTabs.addTab(mainTabs.newTab().setCustomView(R.layout.tab_profile))

        //
        mainTabs.addOnTabSelectedListener(tabSelectedListener)
    }

    private val tabSelectedListener = object: TabLayout.OnTabSelectedListener{
        override fun onTabReselected(p0: TabLayout.Tab?) {

        }

        override fun onTabUnselected(p0: TabLayout.Tab?) {

        }

        override fun onTabSelected(tab: TabLayout.Tab?) {
            setCurrentTab(tab?.position!!)
        }
    }

    private fun setCurrentTab(position: Int) {
        when(position) {
            0 -> {
                openHomeFragment()
            }

            1 -> {
                openNotificationFragment()
            }

            2 -> {
                openProfileFragment()
            }
        }
    }

    private fun openHomeFragment() {
        var homeFragment = supportFragmentManager.findFragmentByTag(HomeFragment::class.simpleName)

        if (homeFragment == null) {
            homeFragment = HomeFragment.newInstance()
        }

        replaceFragment(homeFragment, HomeFragment::class.simpleName)
    }

    private fun openNotificationFragment() {
        var notificationFragment = supportFragmentManager.findFragmentByTag(NotificationFragment::class.simpleName)

        if (notificationFragment == null) {
            notificationFragment = NotificationFragment.newInstance()
        }

        replaceFragment(notificationFragment, NotificationFragment::class.simpleName)
    }

    private fun openProfileFragment() {
        var profileFragment = supportFragmentManager.findFragmentByTag(ProfileFragment::class.simpleName)

        if (profileFragment == null) {
            profileFragment = ProfileFragment.newInstance()
        }

        replaceFragment(profileFragment, ProfileFragment::class.simpleName)
    }

    private fun replaceFragment(fragment: Fragment, tag: String?) {
        supportFragmentManager.beginTransaction().replace(R.id.mainContainer,fragment,tag).addToBackStack(null).commit()
    }

    override fun initData() {

    }
}
