package de.moetz.android.timingiseverything

import android.content.Intent
import android.support.constraint.ConstraintLayout
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import de.moetz.android.timingiseverything.timereg.TimeRegsActivity
import kotlinx.android.synthetic.main.base.*
import kotlinx.android.synthetic.main.base_navigation.*


abstract class BaseActivity(private val toolbarText: String) : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun setContentView(layoutResID: Int) {
        val rootView = layoutInflater.inflate(R.layout.base, null) as DrawerLayout
        val contentContainer = rootView.findViewById<ConstraintLayout>(R.id.content_container)
        layoutInflater.inflate(layoutResID, contentContainer, true)
        super.setContentView(rootView)

        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        toolbar.title = this.toolbarText
        toolbar.inflateMenu(R.menu.main)

        val navDrawerToggle = ActionBarDrawerToggle(
                this, root_view, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        root_view.addDrawerListener(navDrawerToggle)
        navDrawerToggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (root_view.isDrawerOpen(GravityCompat.START)) {
            root_view.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> {
                startActivity(Intent(this, MainActivity::class.java))
            }
            R.id.nav_timereg_show -> {
                startActivity(Intent(this, TimeRegsActivity::class.java))
            }
        }
        root_view.closeDrawer(GravityCompat.START)
        return true
    }

}
