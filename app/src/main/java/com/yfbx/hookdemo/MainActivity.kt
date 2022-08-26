package com.yfbx.hookdemo

import android.app.ActivityManager
import android.content.ContentResolver
import android.hardware.SensorManager
import android.location.LocationManager
import android.net.wifi.WifiInfo
import android.os.Bundle
import android.provider.Settings
import android.telephony.TelephonyManager
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.yfbx.hookdemo.hook.hook
import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.XposedBridge
import de.robv.android.xposed.callbacks.XC_LoadPackage
import java.net.NetworkInterface

class MainActivity : AppCompatActivity(), IXposedHookLoadPackage {
    private val packageEdit: EditText by lazy { findViewById(R.id.package_edit) }
    private val confirmBtn: Button by lazy { findViewById(R.id.confirm_btn) }
    private var pkgName = "com.ypcang.android.shop"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        confirmBtn.setOnClickListener {
            pkgName = packageEdit.text.toString()
        }
    }


    override fun handleLoadPackage(loadPackageParam: XC_LoadPackage.LoadPackageParam?) {
        val packageName = loadPackageParam?.packageName ?: return
        if (packageName != pkgName) return
        XposedBridge.log("\n\n---------开始hook---------")
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
            afterHook { printStack() }
        }

        hook<NetworkInterface> {
            method("getHardwareAddress")
            beforeHook { log("调用getHardwareAddress()获取了mac地址") }
            afterHook { printStack() }
        }

        hook<Settings.Secure> {
            method("getString", ContentResolver::class.java, String::class.java)
            beforeHook { log("调用Settings.Secure获取了${it?.args?.first() ?: ""}") }
            afterHook { printStack() }
        }

        hook<LocationManager> {
            method("getLastKnownLocation", String::class.java)
            beforeHook { log("调用getLastKnownLocation获取了GPS地址") }
            afterHook { printStack() }
        }

        hook<SensorManager> {
            method("getSensorList", Int::class.java)
            beforeHook { log("调用getSensorList获取了传感器信息") }
            afterHook { printStack() }
        }

        hook<SensorManager> {
            method("getDefaultSensor", Int::class.java)
            beforeHook { log("调用getDefaultSensor获取了传感器信息") }
            afterHook { printStack() }
        }

        hook<ActivityManager> {
            method("getRunningAppProcesses")
            beforeHook { log("调用getRunningAppProcesses获取了运行中的进程") }
            afterHook { printStack() }
        }
    }

}

