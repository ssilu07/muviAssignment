package com.royals.muviassignment.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.royals.muviassignment.R
import com.royals.muviassignment.fragment.FavoritesFragment
import com.royals.muviassignment.fragment.HomeFragment
import com.royals.muviassignment.fragment.MoviesFragment
import com.royals.muviassignment.fragment.ProfileFragment
import com.royals.muviassignment.fragment.SettingsFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    replaceFragment(HomeFragment())
                    true
                }

                R.id.nav_movies -> {
                    replaceFragment(MoviesFragment())
                    true
                }

                R.id.nav_favorites -> {
                    replaceFragment(FavoritesFragment())
                    true
                }

                R.id.nav_profile -> {
                    replaceFragment(ProfileFragment())
                    true
                }

                R.id.nav_settings -> {
                    replaceFragment(SettingsFragment())
                    true
                }

                else -> false
            }
        }

        // Set default selection
        if (savedInstanceState == null) {
            bottomNavigationView.selectedItemId = R.id.nav_home
        }

    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}