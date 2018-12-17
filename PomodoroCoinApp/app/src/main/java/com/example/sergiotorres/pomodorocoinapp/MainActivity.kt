package com.example.sergiotorres.pomodorocoinapp

import android.support.v4.app.Fragment
import android.app.FragmentManager
import android.app.FragmentTransaction
import android.app.NotificationManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.view.MenuItem
import com.squareup.leakcanary.LeakCanary
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val clockFragment = ClockFragment()
    val profileFragment = ProfileFragment()
    var fragmentActive:Fragment = clockFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return
        }
        LeakCanary.install(this.application)
        // Normal app init code...
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().add(R.id.fragmentLayout, profileFragment).hide(profileFragment).commit()
        supportFragmentManager.beginTransaction().add(R.id.fragmentLayout, clockFragment).commit()
        bottom_nav_view.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.timer -> {
                    supportFragmentManager.beginTransaction().hide(fragmentActive).show(clockFragment).commit()
                    fragmentActive = clockFragment
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.profile -> {
                    supportFragmentManager.beginTransaction().hide(fragmentActive).show(profileFragment).commit()
                    fragmentActive = profileFragment
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.tasks -> {
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.tags -> {
                    return@setOnNavigationItemSelectedListener true
                }
            }
            return@setOnNavigationItemSelectedListener false
        }

    }



}
