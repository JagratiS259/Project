package com.example.animalshelterapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.viewpager.widget.ViewPager
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    lateinit var drawerLayout: DrawerLayout
    lateinit var coordinatorLayout: CoordinatorLayout
    lateinit var toolbar: Toolbar
    lateinit var frameLayout: FrameLayout
    lateinit var navigationView:NavigationView

var previousMenuItem:MenuItem?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout=findViewById(R.id.drawerLayout)
        coordinatorLayout=findViewById(R.id.coordinatorLayout)
        toolbar=findViewById(R.id.toolbar)
        frameLayout=findViewById(R.id.frame)
        navigationView=findViewById(R.id.navigationView)
        setUpToolbar()

        openDashboard()

        val actionBarDrawerToggle=ActionBarDrawerToggle(
                this@MainActivity,
                drawerLayout,
                R.string.open_drawer,
                R.string.close_drawer
        )
drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        navigationView.setNavigationItemSelectedListener {

            if(previousMenuItem!=null){
                previousMenuItem?.isChecked=false
            }

            it.isCheckable=true
            it.isChecked=true
            previousMenuItem=it

            when(it.itemId){
                R.id.login->{
                    supportFragmentManager.beginTransaction()
                            .replace(R.id.frame,LoginFragment())

                            .commit()
                    supportActionBar?.title="Log In"
                    drawerLayout.closeDrawers()
                }
                R.id.dashboard->{
                    openDashboard()
                    drawerLayout.closeDrawers()

                }
                R.id.profile->{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame,ProfileFragment())

                        .commit()
                    supportActionBar?.title="Profile"
                    drawerLayout.closeDrawers()
                }
                R.id.Settings->{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame,SettingsFragment())

                        .commit()
                    supportActionBar?.title="Settings"
                    drawerLayout.closeDrawers()
                }
                R.id.ContactUs->{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame,ContactUsFragment())

                        .commit()
                    supportActionBar?.title="Contact Us"
                    drawerLayout.closeDrawers()

                }
                R.id.AboutApp->{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame,AboutAppFragment())

                        .commit()
                    supportActionBar?.title="About App"
                    drawerLayout.closeDrawers()

                }
                R.id.LogOut->{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame,LogOutFragment())

                        .commit()
                    supportActionBar?.title="Log Out"
                    drawerLayout.closeDrawers()

                }
            }
            return@setNavigationItemSelectedListener true
        }
    }

    fun setUpToolbar(){
        setSupportActionBar(toolbar)
        supportActionBar?.title="Toolbar Title"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START)
        }
        return super.onOptionsItemSelected(item)
    }
        fun openDashboard(){
            val fragment=DashboardFragment()
            val trasaction=supportFragmentManager.beginTransaction()
            trasaction.replace(R.id.frame,fragment)
            trasaction.commit()
            supportActionBar?.title="Dashboard"
            navigationView.setCheckedItem(R.id.dashboard)
    }

    override fun onBackPressed() {
        val frag=supportFragmentManager.findFragmentById(R.id.frame)

        when(frag){
            !is DashboardFragment->openDashboard()

            else->super.onBackPressed()
        }

    }
}