package com.zhihaofans.server_chan_3rd_party.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.orhanobut.logger.Logger
import com.zhihaofans.server_chan_3rd_party.R
import kotlinx.android.synthetic.main.activity_about.*
import kotlinx.android.synthetic.main.content_about.*
import org.jetbrains.anko.*

class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val items: Array<String> = arrayOf(getString(R.string.text_about) + getString(R.string.text_thisApp), getString(R.string.text_about) + getString(R.string.text_serverChan), getString(R.string.text_sourceCode) + "(Github)", getString(R.string.text_bugs), getString(R.string.text_iconSource))
        lv_about.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, android.R.id.text1, items)
        lv_about.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val itemValue: String = lv_about.getItemAtPosition(position) as String
            Logger.d("选择了$itemValue($position)")
            when (position) {
                0 -> alert(getString(R.string.text_aboutThisApp), getString(R.string.text_about) + getString(R.string.text_thisApp)) { yesButton {} }.show()
                1 -> browse("https://sc.ftqq.com/3.version")
                2 -> browse("https://github.com/zhihaofans/Server-Chan-Android")
                3 -> browse("https://github.com/zhihaofans/Server-Chan-Android/issues/new")
                4 -> alert(getString(R.string.url_iconSource), getString(R.string.text_iconSource)) { okButton { browse(getString(R.string.url_iconSource)) } }.show()
                else -> toast(R.string.text_error)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
