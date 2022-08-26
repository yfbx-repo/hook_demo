package com.yfbx.hookdemo.hook

import de.robv.android.xposed.XC_MethodHook

/**
 * Date: 2022-07-27
 * Author: Edward
 * Desc:
 */

typealias MethodHookCallback = (XC_MethodHook.MethodHookParam?) -> Unit

class HookCallback : XC_MethodHook() {
    private var beforeHookedMethod: MethodHookCallback? = null
    private var afterHookedMethod: MethodHookCallback? = null

    fun beforeHook(beforeHookedMethod: MethodHookCallback) {
        this.beforeHookedMethod = beforeHookedMethod
    }

    fun afterHook(afterHookedMethod: MethodHookCallback) {
        this.afterHookedMethod = afterHookedMethod
    }

    override fun beforeHookedMethod(param: MethodHookParam?) {
        beforeHookedMethod?.invoke(param)
    }

    override fun afterHookedMethod(param: MethodHookParam?) {
        afterHookedMethod?.invoke(param)
        super.afterHookedMethod(param)
    }

    fun getMethodStack(): String {
        val stringBuilder = StringBuilder()
        val stackTraceElements = Thread.currentThread().stackTrace
        for (temp in stackTraceElements) {
            stringBuilder.append(temp.toString()).append("\n")
        }
        return stringBuilder.toString()
    }
}