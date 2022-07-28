package com.yfbx.hookdemo.hook

import android.content.ContentResolver
import android.hardware.SensorManager
import android.location.LocationManager
import android.net.wifi.WifiInfo
import android.provider.Settings
import android.telephony.TelephonyManager
import com.yfbx.hookdemo.App
import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.XposedBridge
import de.robv.android.xposed.callbacks.XC_LoadPackage
import java.net.NetworkInterface

class HookPrivacyKotlin : IXposedHookLoadPackage {

    override fun handleLoadPackage(loadPackageParam: XC_LoadPackage.LoadPackageParam?) {
        val packageName = loadPackageParam?.packageName ?: return
        if (packageName != App.packageName) return

        XposedBridge.log("---------开始hook---------")
        XposedBridge.log("包名：$packageName")


        hook<TelephonyManager> {
            method("getDeviceId")
            beforeHook { log("调用getDeviceId()获取了imei") }
            afterHook { printStack() }
        }

        hook<TelephonyManager> {
            method("getDeviceId", Int::class.java)
            beforeHook { log("调用getDeviceId()获取了imei") }
            afterHook { printStack() }
        }

        hook<TelephonyManager> {
            method("getSubscriberId")
            beforeHook { log("调用getSubscriberId()获取了imsi") }
            afterHook { printStack() }
        }

        hook<WifiInfo> {
            method("getMacAddress")
            beforeHook { log("调用getMacAddress()获取了mac地址") }
        }

        hook<NetworkInterface> {
            method("getHardwareAddress")
            beforeHook { log("调用getHardwareAddress()获取了mac地址") }
        }

        hook<Settings.Secure> {
            method("getString", ContentResolver::class.java, String::class.java)
            beforeHook { log("调用Settings.Secure获取了${it?.args?.first() ?: ""}") }
        }

        hook<LocationManager> {
            method("getLastKnownLocation", String::class.java)
            beforeHook { log("调用getLastKnownLocation获取了GPS地址") }
        }

        hook<SensorManager> {
            method("getSensorList", Int::class.java)
            beforeHook { log("调用getSensorList获取了传感器信息") }
        }

        hook<SensorManager> {
            method("getDefaultSensor", Int::class.java)
            beforeHook { log("调用getDefaultSensor获取了传感器信息") }
        }
    }
}

