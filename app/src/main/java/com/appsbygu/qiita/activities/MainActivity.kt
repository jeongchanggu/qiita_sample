package com.appsbygu.qiita.activities

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import com.appsbygu.qiita.R
import com.appsbygu.qiita.adapters.TabAdapter
import com.appsbygu.qiita.fragments.CardContentFragment
import com.appsbygu.qiita.fragments.ListContentFragment
import com.appsbygu.qiita.fragments.TileContentFragment

import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
        nav_view.menu.getItem(0).isChecked = true
        initTab()
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_tag -> {
                val intent = TagActivity.createIntent(this)
                startActivity(intent)
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onDestroy() {
        disposables.dispose()
        super.onDestroy()
    }

    override fun onResume() {
        nav_view.menu.getItem(0).isChecked = true
        super.onResume()
    }

    private fun initTab() {
        val tabAdapter = TabAdapter(supportFragmentManager)

        val listFragment = ListContentFragment()
        listFragment.setProgressCallback { changeUnderProgressbarStatus(it) }
        listFragment.setOnclickCallback { onClickedArticle(it) }

        val tileFragment = TileContentFragment()
        tileFragment.setProgressCallback { changeUnderProgressbarStatus(it) }
        tileFragment.setOnclickCallback { onClickedArticle(it) }

        val cardFragment = CardContentFragment()
        cardFragment.setProgressCallback { changeUnderProgressbarStatus(it) }
        cardFragment.setOnclickCallback { onClickedArticle(it) }

        tabAdapter.addTab(listFragment, getString(R.string.tab_name_list))
        tabAdapter.addTab(tileFragment, getString(R.string.tab_name_tile))
        tabAdapter.addTab(cardFragment, getString(R.string.tab_name_card))
        viewPager.adapter = tabAdapter
        viewPager.offscreenPageLimit = 2
        tabs.setupWithViewPager(viewPager)
    }

    private fun changeUnderProgressbarStatus(visibility: Int) {
        underProgressbar.visibility = visibility
    }

    private fun onClickedArticle(html: String) {
        val intent = ArticleActivity.createIntent(this, html)
        startActivity(intent)
    }

}
