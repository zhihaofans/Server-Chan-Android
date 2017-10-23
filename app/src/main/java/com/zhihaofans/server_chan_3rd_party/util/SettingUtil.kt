package com.zhihaofans.server_chan_3rd_party.util

import android.annotation.SuppressLint
import android.content.Context
import android.preference.PreferenceManager
import android.util.Log

/**
 * Created by zhihaofans on 2017/10/23.
 */
class SettingUtil {
    private var context: Context? = null

    fun setContext(_context: Context) {
        context = _context
    }

    @SuppressLint("ApplySharedPref")
    fun setStr(name: String, value: String): Boolean {
        return PreferenceManager.getDefaultSharedPreferences(context).edit().putString(name, value).commit()
    }

    fun getStr(name: String, default: String): String {
        val str = PreferenceManager.getDefaultSharedPreferences(context).getString(name, default)
        if (str == default) {
            if (!setStr(name, default)) {
                Log.e("set on getString", "NO")
            }
        }
        return str
    }

    @SuppressLint("ApplySharedPref")
    fun setBoolean(name: String, value: Boolean): Boolean {
        return PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean(name, value).commit()
    }

    fun getBoolean(name: String, default: Boolean): Boolean {
        val bool = PreferenceManager.getDefaultSharedPreferences(context).getBoolean(name, default)
        if (bool == default) {
            if (!setBoolean(name, default)) {
                Log.e("set on getBoolean", "NO")
            }
        }
        return bool
    }
}