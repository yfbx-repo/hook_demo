package com.yfbx.hookdemo.hook

import de.robv.android.xposed.XposedBridge
import de.robv.android.xposed.XposedHelpers

/**
 * Date: 2022-07-28
 * Author: Edward
 * Desc:
 */

inline fun <reified T> hook(init: HookBuilder.() -> Unit) {
    val builder = HookBuilder().apply(init).build()
    XposedHelpers.findAndHookMethod(T::class.java, builder.methodName, *builder.args.toArray())
}


class HookBuilder {
    var methodName: String? = null
    val args = arrayListOf<Any>()
    private val callback = HookCallback()


    fun method(methodName: String, vararg params: Any) {
        this.methodName = methodName
        args.addAll(params)
    }

    fun beforeHook(before: MethodHookCallback) {
        callback.beforeHook(before)
    }

    fun afterHook(after: MethodHookCallback) {
        callback.afterHook(after)
    }

    fun build(): HookBuilder {
        args.add(callback)
        return this
    }

    fun log(text: String) {
        XposedBridge.log("\n--->$text")
    }

    fun printStack() {
        log(callback.getMethodStack())
        log("<---\n")
    }

}