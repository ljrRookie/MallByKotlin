package com.ljr.baselibrary.utils

import android.util.Log
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import android.R.attr.tag
import android.R.id.message



/**
 * Created by 林佳荣 on 2018/6/15.
 * Github：https://github.com/ljrRookie
 * Function ：
 */
object LogUtils {
    val LINE_SEPARATOR = System.getProperty("line.separator")

    fun printLine(tag: String, isTop: Boolean) {
        if (isTop) {
            Log.e(tag, "╔═══════════════════════════════════════════════════════════════════════════════════════")
        } else {
            Log.e(tag, "╚═══════════════════════════════════════════════════════════════════════════════════════")
        }
    }

    fun printJson(tag: String, msg: String) {
        var message: String = ""
        if (msg.startsWith("{")) {
            if (msg.startsWith("{")) {
                val jsonObject = JSONObject(msg)
                message = jsonObject.toString(4)//最重要的方法，就一行，返回格式化的json字符串，其中的数字4是缩进字符数

            } else if (msg.startsWith("[")) {
                val jsonArray = JSONArray(msg)
                message = jsonArray.toString(4)
            }
          printLine(tag,true)
            message = message
            val lines = message.split(LINE_SEPARATOR.toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            for (line in lines) {
                Log.e(tag, "║ " + line)
            }
            printLine(tag,false)
        } else {
            message = msg
            Log.e(tag, message)
        }



    }


}