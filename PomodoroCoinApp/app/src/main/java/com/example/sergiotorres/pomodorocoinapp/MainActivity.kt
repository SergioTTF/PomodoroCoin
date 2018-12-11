package com.example.sergiotorres.pomodorocoinapp

import android.app.Fragment
import android.app.FragmentManager
import android.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

//    val clockFragment = ClockFragment()
//    val fragmentActive = clockFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val clockFragment = ClockFragment()
        supportFragmentManager.beginTransaction().add(R.id.fragmentLayout, clockFragment, "1").commit()
        //supportFragmentManager.beginTransaction().add(R.id.fragmentLayout, ClockFragment(),"1").commit()

    }

//    private val bottomNavListener = BottomNavigationView.OnNavigationItemSelectedListener {
//        fun onNavigationItemSelected(item: MenuItem): Boolean {
////            when (item.itemId) {
////                R.id.timer ->
////            }
//            return false
//        }
//
//    }


}
