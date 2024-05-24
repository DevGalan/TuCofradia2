package com.devgalan.tucofradia2.ui

import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.devgalan.tucofradia2.R
import com.devgalan.tucofradia2.databinding.ActivityMainBinding
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var toolbar: MaterialToolbar

    private lateinit var drawerNavigationView: NavigationView
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        initUI()
    }

    private fun initUI() {
        navController = findNavController(R.id.navHostFragment)
        setToolbar()
        setDrawer()
    }

    private fun setToolbar() {
        toolbar = binding.mainView.mainToolbar.toolbar

        setSupportActionBar(toolbar)
    }

    private fun setDrawer() {
        drawerLayout = binding.drawerLayout
        drawerNavigationView = binding.navView

        toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        setTitle(R.string.home)

        setNavigationView()
    }

    private fun setNavigationView() {
        drawerNavigationView.setNavigationItemSelectedListener {

            setTitle(it.title)

            when(it.itemId) {
                R.id.nav_home -> {
                    navController.navigate(R.id.homeFragment)
                }
                R.id.nav_profile -> {
                    navController.navigate(R.id.profileFragment)
                }
            }

            val navController = navController
            navController.addOnDestinationChangedListener { _, destination, _ ->
                setTitle(destination.label)
            }

            drawerLayout.closeDrawers()
            true
        }
    }

//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        menuInflater.inflate(R.menu.main_toolbar_menu, menu)
//        return true
//    }
//
//    override fun onSupportNavigateUp(): Boolean {
//        val navController = findNavController(R.id.navHostFragment)
//        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//
//        if (toggle.onOptionsItemSelected(item)) {
//            return true
//        }
//        return super.onOptionsItemSelected(item)
//    }
}
