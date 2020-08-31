package com.adith.smash.x.instagram

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.adith.smash.x.instagram.fragments.HomeFragment
import com.adith.smash.x.instagram.fragments.NotificationFragment
import com.adith.smash.x.instagram.fragments.ProfileFragment
import com.adith.smash.x.instagram.fragments.SearchFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    public val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when(item.itemId){
            R.id.nav_home ->{
                moveToFragment(HomeFragment())
                return@OnNavigationItemSelectedListener true
            }

            R.id.nav_search ->{
                moveToFragment(SearchFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_add_post ->{
                item.isChecked = false
                startActivity(Intent(this, TambahPostActivity::class.java))
                return@OnNavigationItemSelectedListener true
            }

            R.id.nav_notification ->{
                moveToFragment(NotificationFragment)
                return@OnNavigationItemSelectedListener true
            }

            R.id.nav_profile ->{
                moveToFragment(ProfileFragment())
                return@OnNavigationItemSelectedListener true
            }
        }

        false
    }

    private fun moveToFragment(fragment: NotificationFragment.Companion) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //untuk membuild Bottom Navigation nya
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        //supaya home menjadi default saat applikasi di run
        moveToFragment(HomeFragment())
    }

    // function untuk berpindah antar fragment
    private fun moveToFragment(fragment: Fragment) {
        val fragmentTrans = supportFragmentManager.beginTransaction()
        fragmentTrans.replace(R.id.fragmen_container, fragment)
        fragmentTrans.commit()
    }

}
