package com.zhihaofans.server_chan_3rd_party.action

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.zhihaofans.server_chan_3rd_party.view.StartActivity
import java.util.*
import android.util.Log
import org.jetbrains.anko.startActivity


/**
 * Created by zhihaofans on 2017/10/30.
 */
class BrowserActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val TAG = "BrowserActivity"
        val uri = intent.data
        if (uri != null) {
            // 完整的url信息
            val url = uri.toString()
            Log.d(TAG, "url: " + uri)
            // scheme部分
            val scheme = uri.scheme
            Log.d(TAG, "scheme: " + scheme)
            // host部分
            val host = uri.host
            Log.d(TAG, "host: " + host)
            //port部分
            val port = uri.port
            Log.d(TAG, "host: " + port)
            // 访问路劲
            val path = uri.path
            Log.d(TAG, "path: " + path)
            // Query部分
            val query = uri.query
            Log.d(TAG, "query: " + query)
            startActivity<StartActivity>("shareText" to url)
        } else {
            finish()
        }
    }
}