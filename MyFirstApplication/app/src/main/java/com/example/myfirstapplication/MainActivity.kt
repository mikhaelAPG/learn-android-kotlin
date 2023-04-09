package com.example.myfirstapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i("Main Activity", "onCreate")

        bottom_navigation.setOnNavigationItemSelectedListener(onBottomNavListener)
        bottom_navigation.getOrCreateBadge(R.id.item_explore).apply {
            number = 20
            isVisible = true
            backgroundColor = resources.getColor(R.color.red)
        }
        var fr = supportFragmentManager.beginTransaction()
        fr.add(R.id.fl_fragment, HomeFragment())
        fr.commit()
    }

    private val onBottomNavListener = BottomNavigationView.OnNavigationItemSelectedListener { i->
        var selectedFr: Fragment = HomeFragment()

        when(i.itemId) {
            R.id.item_home -> {
                selectedFr = HomeFragment()
            }

            R.id.item_explore -> {
                selectedFr = ExploreFragment()
                bottom_navigation.getOrCreateBadge(R.id.item_explore).apply {
                    number = 0
                    isVisible = false
                }
            }

            R.id.item_subscriptions -> {
                selectedFr = SubscriptionsFragment()
            }

            R.id.item_library -> {
                selectedFr = LibraryFragment()
            }
        }
        var fr = supportFragmentManager.beginTransaction()
        fr.replace(R.id.fl_fragment, selectedFr)
        fr.commit()
        true
    }
}