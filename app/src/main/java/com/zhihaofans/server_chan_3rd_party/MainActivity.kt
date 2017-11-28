package com.zhihaofans.server_chan_3rd_party

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.zhihaofans.server_chan_3rd_party.view.StartActivity
import org.jetbrains.anko.startActivity
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Logger.addLogAdapter(AndroidLogAdapter())
        Logger.d("Start from MainActivity")
        startActivity<StartActivity>("shareText" to "")
    }
}
