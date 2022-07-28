package com.yfbx.hookdemo


import android.app.Application
import kotlin.properties.Delegates

/**
 * Created by
 * 应用入口
 */

class App : Application() {


    companion object {
        var instance by Delegates.notNull<App>()
        val packageName = "com.ypcang.android.shop"
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}
