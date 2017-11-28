package com.zhihaofans.server_chan_3rd_party.action

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.zhihaofans.server_chan_3rd_party.view.StartActivity
import org.jetbrains.anko.startActivity
import java.util.*


/**
 * Created by zhihaofans on 2017/10/30.
 */
class ShareActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = intent
        if (Objects.equals(Intent.ACTION_SEND, intent.action) && intent.type != null && Objects.equals("text/plain", intent.type)) {
            val st = intent.getStringExtra(Intent.EXTRA_TEXT)
            startActivity<StartActivity>("shareText" to st)
        } else {
            finish()
        }
    }
}