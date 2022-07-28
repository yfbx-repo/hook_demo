package com.yfbx.hookdemo.hook;

import android.content.ContentResolver;
import android.location.LocationManager;

import com.yfbx.hookdemo.App;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class HookPrivacy extends XC_MethodHook implements IXposedHookLoadPackage {

    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam param) {
        if (param == null) {
            return;
        }
        /*判断hook的包名*/
        if (!param.packageName.equals(App.Companion.getPackageName())) {
            return;
        }


        XposedBridge.log("---------开始hook---------");
        XposedBridge.log("包名：" + param.packageName);


        hook(
                android.telephony.TelephonyManager.class,
                "getDeviceId",
                this
        );

        hook(
                android.telephony.TelephonyManager.class,
                "getDeviceId",
                int.class,
                this
        );

        hook(
                android.telephony.TelephonyManager.class,
                "getSubscriberId",
                this
        );

        hook(
                android.net.wifi.WifiInfo.class,
                "getMacAddress",
                this
        );

        hook(
                java.net.NetworkInterface.class,
                "getHardwareAddress",
                this
        );

        hook(
                android.provider.Settings.Secure.class,
                "getString",
                ContentResolver.class,
                String.class,
                this
        );

        hook(
                LocationManager.class,
                "getLastKnownLocation",
                String.class,
                this
        );


        hook(
                android.hardware.SensorManager.class,
                "getSensorList",
                int.class,
                this

        );

        hook(
                android.hardware.SensorManager.class,
                "getDefaultSensor",
                int.class,
                this
        );
    }

    /**
     * @param parameterTypesAndCallback 要hook的方法有参数时，需要传入参数类型class, 回调必须放最后
     */
    private void hook(Class<?> clazz, String methodName, Object... parameterTypesAndCallback) {
        XposedHelpers.findAndHookMethod(clazz, methodName, parameterTypesAndCallback);
    }

    @Override
    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
        XposedBridge.log("-------------------> Hook到方法调用");
    }

    @Override
    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
        XposedBridge.log(getMethodStack());
        XposedBridge.log("<---\n");
        super.afterHookedMethod(param);
    }


    private String getMethodStack() {
        StringBuilder stringBuilder = new StringBuilder();
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        for (StackTraceElement temp : stackTraceElements) {
            stringBuilder.append(temp.toString()).append("\n");
        }
        return stringBuilder.toString();
    }

}
