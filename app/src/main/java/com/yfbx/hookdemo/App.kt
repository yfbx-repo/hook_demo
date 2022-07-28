package com.yfbx.hookdemo


import android.app.Application
import com.yfbx.hookdemo.utils.pref
import kotlin.properties.Delegates

/**
 * Created by
 * 应用入口
 */

class App : Application() {

    companion object {
        var instance by Delegates.notNull<App>()
        var hookPackage by pref("")
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}
