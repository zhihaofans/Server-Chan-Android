package com.zhihaofans.server_chan_3rd_party.util

import android.util.Log
import okhttp3.*
import org.json.JSONObject
import java.io.IOException


/**
 * Created by zhihaofans on 2017/10/22.
 */
class ServerChanUtil {
    private var sckey: String = ""
    private val maxText: Int = 256
    private val maxDesp: Int = 32768
    private var text: String = ""
    private var desp: String = ""
    private var errorStr: String = ""
    fun setKey(key: String) {
        sckey = key
    }

    fun getKey(): String {
        return sckey
    }

    fun get(t: String): Int {
        return get(t, "")
    }

    fun get(t: String, d: String): Int {
        text = t
        desp = d
        errorStr = ""
        if (text.isNotEmpty()) {
            if (text.length <= maxText) {
                if (desp.length <= maxDesp) {
                    var serverUrl = "https://sc.ftqq.com/$sckey.send?text=$text"
                    if (desp.isNotEmpty()) {
                        serverUrl = "$serverUrl&desp=$desp"
                    }
                    try {
                        val response = OkHttpClient().newCall(Request.Builder().get().url(serverUrl).build()).execute()
                        Log.d("getVideoJson", "code:${response.code()}")
                        return if (response.isSuccessful) {
                            var serverJson: String = response.body()!!.string()
                            if (serverJson.isEmpty()) {
                                return 5//返回结果空白
                            }
                            if (serverJson.startsWith("\ufeff")) {
                                serverJson = serverJson.substring(1)
                            }

                            Log.d("ServerChan.get", serverJson)
                            val s = JSONObject(serverJson)
                            val errno: Int = s.getInt("errno")
                            val errmsg: String = s.getString("errmsg")
                            when (errno) {
                                1024 -> errorStr = errmsg
                            }
                            errno
                        } else {
                            //......
                            Log.e("getVideoJson", "code:${response.code()}")
                            return response.code()//网络错误
                        }
                    } catch (e: IOException) {
                        e.printStackTrace()
                        Log.e("getVideoJson", "error:$e")
                        return 4//推送或解析返回结果失败
                    }
                } else {
                    return 3//文本内容不得超过 $maxDesp 个字符
                }
            } else {
                return 2//标题不得超过 $maxText 个字符
            }
        } else {
            return 1//标题不能为空
        }
    }

    fun post(t: String, d: String): Int {
        text = t
        desp = d
        errorStr = ""
        if (text.isNotEmpty()) {
            if (text.length <= maxText) {
                if (desp.length <= maxDesp) {
                    val serverUrl = "https://sc.ftqq.com/$sckey.send"
                    val okHttpClient = OkHttpClient()
                    var body: RequestBody = FormBody.Builder().add("text", text).build()
                    if (desp.isNotEmpty()) {
                        body = FormBody.Builder().add("text", text).add("desp", desp).build()
                    }
                    val request: Request = Request.Builder().url(serverUrl).post(body).build()
                    val call: Call = okHttpClient.newCall(request)
                    try {
                        val response = call.execute()
                        Log.d("getVideoJson", "code:${response.code()}")
                        return if (response.isSuccessful) {
                            var serverJson: String = response.body()!!.string()
                            if (serverJson.isEmpty()) {
                                return 5//返回结果空白
                            }
                            if (serverJson.startsWith("\ufeff")) {
                                serverJson = serverJson.substring(1)
                            }

                            Log.d("ServerChan.get", serverJson)
                            val s = JSONObject(serverJson)
                            val errno: Int = s.getInt("errno")
                            val errmsg: String = s.getString("errmsg")
                            when (errno) {
                                1024 -> errorStr = errmsg
                            }
                            errno
                        } else {
                            //......
                            Log.e("getVideoJson", "code:${response.code()}")
                            return response.code()//网络错误
                        }
                    } catch (e: IOException) {
                        e.printStackTrace()
                        Log.e("getVideoJson", "error:$e")
                        return 4//推送或解析返回结果失败
                    }
                } else {
                    return 3//文本内容不得超过 $maxDesp 个字符
                }
            } else {
                return 2//标题不得超过 $maxText 个字符
            }
        } else {
            return 1//标题不能为空
        }
    }

    fun post(t: String): Int {
        return post(t, "")
    }

    fun maxText(): Int {
        return maxText
    }

    fun maxDesp(): Int {
        return maxDesp
    }

    fun getError(errorCode: Int): String {
        return when (errorCode) {
            0 -> "成功"
            1 -> "标题不能为空"
            2 -> "标题不得超过 $maxText 个字符"
            3 -> "文本内容不得超过 $maxDesp 个字符"
            4 -> "推送或解析返回结果失败"
            5 -> "返回结果空白"
            301 -> "301 Moved Permanently"
            302 -> "302 Found"
            304 -> "304 Not Modified"
            400 -> "400 Bad Request"
            401 -> "401 Unauthorized"
            403 -> "403 Forbidden"
            404 -> "404 Not Found"
            500 -> "500 Internal Server Error"
            1024 -> {
                when (errorStr) {
                    "bad pushtoken" -> "错误的SCKEY"
                    "不要重复发送同样的内容" -> "不要重复发送同样的内容"
                    else -> "未知错误1024($errorStr)"
                }
            }
            -1 -> "网络错误"
            else -> "未知错误($errorCode)"
        }
    }
}