package com.devgalan.tucofradia2.ui

import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.devgalan.tucofradia2.R
import com.devgalan.tucofradia2.databinding.ActivityMainBinding
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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

        toolbar = binding.mainView.toolbar

        toolbar.inflateMenu(R.menu.main_toolbar_menu)

        toolbar.setTitle(R.string.home)

        toolbar.setOnMenuItemClickListener { menuItem ->
            true
        }
    }

    private fun setDrawer() {
        drawerLayout = binding.drawerLayout
        drawerNavigationView = binding.navView

        toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.drawer_open,
            R.string.drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        toolbar.setTitle(R.string.home)

        setNavigationView()
    }

    private fun setNavigationView() {
        drawerNavigationView.setNavigationItemSelectedListener {
            navController.navigate(it.itemId)

            val navController = navController
            navController.addOnDestinationChangedListener { _, destination, _ ->
                toolbar.setTitle(destination.label)
            }

            drawerLayout.closeDrawers()
            true
        }
    }
}
