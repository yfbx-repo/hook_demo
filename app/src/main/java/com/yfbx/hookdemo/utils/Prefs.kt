package com.yfbx.hookdemo.utils

/**
 * Date: 2021-10-21
 * Author: Edward
 * Desc:
 */

import android.content.Context
import android.content.SharedPreferences
import com.yfbx.hookdemo.App
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Author: Edward
 * Date: 2020-06-24
 * Description: SharedPreferences
 */

/**
 * @param defaultValue 默认值
 * eg. var version by pref("1.0.0"), 默认 key 即变量名 "version"
 */
inline fun <reified T> pref(defaultValue: T) = object : ReadWriteProperty<Any?, T> {

    private val prefs by lazy { prefFile("prefs") }

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        val value = prefs.all[property.name]
        return if (value is T) value else defaultValue
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        val key = property.name
        when (value) {
            is String -> prefs.edit().putString(key, value).apply()
            is Boolean -> prefs.edit().putBoolean(key, value).apply()
            is Int -> prefs.edit().putInt(key, value).apply()
            is Float -> prefs.edit().putFloat(key, value).apply()
            is Long -> prefs.edit().putLong(key, value).apply()
            else -> throw Exception("Unsupported Type")
        }
    }
}

fun prefFile(name: String): SharedPreferences {
    return App.instance.getSharedPreferences(name, Context.MODE_PRIVATE)
}

fun clearPref(name: String) {
    prefFile(name).edit().clear().apply()
}