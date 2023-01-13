package com.lwy.bluebird

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        //底部导航栏 BOTTOM NAVIGATION
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host)
        val navController = navHostFragment!!.findNavController()
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener {_,destination,_ ->
            if(destination.id == R.id.profileFragment || destination.id == R.id.publicFragment){
                bottomNavigationView.visibility = View.VISIBLE
            }
            else{
                bottomNavigationView.visibility = View.GONE
            }
        }
    }
}