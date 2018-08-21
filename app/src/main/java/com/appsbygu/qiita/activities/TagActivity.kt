package com.appsbygu.qiita.activities

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.appsbygu.qiita.R
import com.appsbygu.qiita.adapters.TagAdapter
import com.appsbygu.qiita.models.tag.Tag
import com.appsbygu.qiita.services.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_tag.*

class TagActivity : AppCompatActivity() {

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, TagActivity::class.java)
        }
    }

    private val disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tag)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.tag_title)

        fetchTagList()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun fetchTagList() {
        var dispose = ApiService.instance.getService()
                .tagList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::afterTagLoaded, this::afterTagLoadedFail)
        disposables.add(dispose)
    }

    private fun afterTagLoaded(tags: ArrayList<Tag>) {
        val adapter = TagAdapter(tags)
        tagListRecyclerView.setHasFixedSize(true)
        tagListRecyclerView.adapter = adapter
        tagListRecyclerView.layoutManager = LinearLayoutManager(this)
        underProgressbar.visibility = View.INVISIBLE
    }

    private fun afterTagLoadedFail(error: Throwable) {
        Toast.makeText(this, getString(R.string.error_text_load_api), Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        disposables.dispose()
        super.onDestroy()
    }
}
