package com.zhihaofans.server_chan_3rd_party

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.zhihaofans.server_chan_3rd_party.view.StartActivity
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.debug
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity(), AnkoLogger {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        debug("Start from MainActivity")
        startActivity<StartActivity>("shareText" to "")
    }
}
