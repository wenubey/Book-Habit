package com.mertfatih.bookhabit

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.navigation.NavigationView
import com.mertfatih.bookhabit.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding
    //private lateinit var toolbar: Toolbar




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        navController = navHostFragment.navController
        NavigationUI.setupActionBarWithNavController(this, navController, binding.drawerLayout)
        NavigationUI.setupWithNavController(binding.navView, navController)
        binding.navView.setNavigationItemSelectedListener(this)


        setupActionBarWithNavController(navController)

    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onContextItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {

            R.id.addBook -> {
                (this as AppCompatActivity).supportActionBar?.let {
                    it.title = "Home"
                }
                val destination = R.id.addFragment
                if(isValidDestination(destination)) {
                    navController.navigate(destination)
                }
            }
            R.id.statisticScreen -> {
                (this as AppCompatActivity).supportActionBar?.let {
                    it.title = "Statistics"
                }
                val destination = R.id.statisticFragment
                if(isValidDestination(destination)) {
                    navController.navigate(destination)
                }
            }

        }
        item.isChecked = true
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun isValidDestination(destination: Int): Boolean {
        return destination != navController.currentDestination!!.id
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, binding.drawerLayout)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.statisticScreen -> {
                return binding.drawerLayout.isDrawerOpen(GravityCompat.START)
            }
            R.id.addBook -> {
                return binding.drawerLayout.isDrawerOpen(GravityCompat.START)
            }
        }
        return super.onOptionsItemSelected(item)
    }

}