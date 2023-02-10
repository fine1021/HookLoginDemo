package com.example.hooklogin;

import android.content.ClipboardManager;
import android.content.ContentResolver;
import android.location.LocationManager;
import android.net.wifi.WifiInfo;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.net.NetworkInterface;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class HookLogin implements IXposedHookLoadPackage {
    private static final String TAG = "HookLogin";

    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam lpparam) {

        if (lpparam == null) {
            return;
        }

        Log.e(TAG, "Load app packageName:" + lpparam.packageName);
        /*判断hook的包名*/

//        if (!MyApplication.pageName.equals(lpparam.packageName)  && !"com.enhance.kaomanfen.yasilisteningapp".equals(lpparam.packageName)
//        && !"com.tal.tiku".equals(lpparam.packageName)) {
//            return;
//        }

//        //固定格式
//        XposedHelpers.findAndHookMethod(
//                "com.", // 需要hook的方法所在类的完整类名
//                lpparam.classLoader,                            // 类加载器，固定这么写就行了
//                "attachBaseContext",                     // 需要hook的方法名
//                Context.class,
//                new XC_MethodHook() {
//                    @Override
//                    protected void beforeHookedMethod(MethodHookParam param) {
//                        XposedBridge.log("调用getDeviceId()获取了imei");
//                    }
//
//                    @Override
//                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                        XposedBridge.log(getMethodStack());
//                        super.afterHookedMethod(param);
//                    }
//                }
//        );

        //固定格式
        XposedHelpers.findAndHookMethod(
                TelephonyManager.class.getName(), // 需要hook的方法所在类的完整类名
                lpparam.classLoader,                            // 类加载器，固定这么写就行了
                "getDeviceId",                     // 需要hook的方法名
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) {
                        XposedBridge.log("调用getDeviceId()获取了imei");
                    }

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        XposedBridge.log("调用getDeviceId()获取了imei完成 " + lpparam.processName);
                        XposedBridge.log(getMethodStack());
                        super.afterHookedMethod(param);
                    }
                }
        );
        XposedHelpers.findAndHookMethod(
                TelephonyManager.class.getName(),
                lpparam.classLoader,
                "getDeviceId",
                int.class,
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) {
                        XposedBridge.log("调用getDeviceId(int)获取了deviceId " + param.args[0]);
                    }

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        XposedBridge.log("调用getDeviceId(int)获取了deviceId完成 " + lpparam.processName);
                        XposedBridge.log(getMethodStack());
                        super.afterHookedMethod(param);
                    }
                }
        );

        XposedHelpers.findAndHookMethod(
                TelephonyManager.class.getName(),
                lpparam.classLoader,
                "getImei",
                int.class,
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) {
                        XposedBridge.log("调用getImei(int)获取了imei " + param.args[0]);
                    }

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        XposedBridge.log("调用getImei(int)获取了imei完成 " + lpparam.processName);
                        XposedBridge.log(getMethodStack());
                        super.afterHookedMethod(param);
                    }
                }
        );

        XposedHelpers.findAndHookMethod(
                TelephonyManager.class.getName(),
                lpparam.classLoader,
                "getMeid",
                int.class,
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) {
                        XposedBridge.log("调用getMeid(int)获取了meid " + param.args[0]);
                    }

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        XposedBridge.log("调用getMeid(int)获取了meid完成 " + lpparam.processName);
                        XposedBridge.log(getMethodStack());
                        super.afterHookedMethod(param);
                    }
                }
        );

//            XposedHelpers.findAndHookMethod(
//                    "com.android.internal.telephony.PhoneSubInfo",
//                    lpparam.classLoader,
//                    "getDeviceId",
//                    new XC_MethodHook() {
//                        @Override
//                        protected void beforeHookedMethod(MethodHookParam param) {
//                            XposedBridge.log("调用PhoneSubInfo的getDeviceId()获取了imei");
//                        }
//                    }
//            );

        XposedHelpers.findAndHookMethod(
                TelephonyManager.class.getName(),
                lpparam.classLoader,
                "getSubscriberId",
                int.class,
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) {
                        XposedBridge.log("调用getSubscriberId获取了imsi " + param.args[0]);
                    }

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        XposedBridge.log("调用getSubscriberId获取了imsi完成 " + lpparam.processName);
                        XposedBridge.log(getMethodStack());
                        super.afterHookedMethod(param);
                    }
                }
        );

        XposedHelpers.findAndHookMethod(
                WifiInfo.class.getName(),
                lpparam.classLoader,
                "getMacAddress",
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) {
                        XposedBridge.log("调用getMacAddress()获取了mac地址");
                    }

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        XposedBridge.log("调用getMacAddress()获取了mac地址完成 " + lpparam.processName);
                        XposedBridge.log(getMethodStack());
                        super.afterHookedMethod(param);
                    }
                }
        );

        XposedHelpers.findAndHookMethod(
                NetworkInterface.class.getName(),
                lpparam.classLoader,
                "getHardwareAddress",
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) {
                        XposedBridge.log("调用getHardwareAddress()获取了mac地址");
                    }

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        XposedBridge.log("调用getHardwareAddress()获取了mac地址完成 " + lpparam.processName);
                        XposedBridge.log(getMethodStack());
                        super.afterHookedMethod(param);
                    }
                }
        );

        XposedHelpers.findAndHookMethod(
                Settings.Secure.class.getName(),
                lpparam.classLoader,
                "getStringForUser",
                ContentResolver.class,
                String.class,
                int.class,
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) {
                        XposedBridge.log("调用Settings.Secure.getStringForUser获取了" + param.args[1]);
                    }

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        XposedBridge.log("调用Settings.Secure.getStringForUser获取了" + param.args[1] + "完成 " + lpparam.processName);
                        XposedBridge.log(getMethodStack());
                        super.afterHookedMethod(param);
                    }
                }
        );

//        Settings.Secure.getString -> Settings.Secure.getStringForUser
//        XposedHelpers.findAndHookMethod(
//                android.provider.Settings.Secure.class.getName(),
//                lpparam.classLoader,
//                "getString",
//                ContentResolver.class,
//                String.class,
//                new XC_MethodHook() {
//                    @Override
//                    protected void beforeHookedMethod(MethodHookParam param) {
//                        XposedBridge.log("调用Settings.Secure.getString获取了" + param.args[1]);
//                    }
//
//                    @Override
//                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                        XposedBridge.log("调用Settings.Secure.getString获取了" + param.args[1] + "完成 " + lpparam.processName);
//                        XposedBridge.log(getMethodStack());
//                        super.afterHookedMethod(param);
//                    }
//                }
//        );

        XposedHelpers.findAndHookMethod(
                LocationManager.class.getName(),
                lpparam.classLoader,
                "getLastKnownLocation",
                String.class,
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) {
                        XposedBridge.log("调用getLastKnownLocation获取了GPS地址");
                    }

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        XposedBridge.log("调用getLastKnownLocation获取了GPS地址完成 " + lpparam.processName);
                        XposedBridge.log(getMethodStack());
                        super.afterHookedMethod(param);
                    }
                }
        );

        XposedHelpers.findAndHookMethod(
                "android.app.ApplicationPackageManager",
                lpparam.classLoader,
                "getInstalledApplications",
                int.class,
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) {
                        XposedBridge.log("调用getInstalledApplications获取了应用安装列表");
                    }

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        XposedBridge.log("调用getInstalledApplications获取了应用安装列表完成 " + lpparam.processName);
                        XposedBridge.log(getMethodStack());
                        super.afterHookedMethod(param);
                    }
                }
        );

//        XposedHelpers.findAndHookMethod(
//                "android.app.ApplicationPackageManager",
//                lpparam.classLoader,
//                "getInstalledApplicationsAsUser",
//                int.class,
//                int.class,
//                new XC_MethodHook() {
//                    @Override
//                    protected void beforeHookedMethod(MethodHookParam param) {
//                        XposedBridge.log("调用getInstalledApplicationsAsUser获取了应用安装列表");
//                    }
//
//                    @Override
//                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                        XposedBridge.log("调用getInstalledApplicationsAsUser获取了应用安装列表完成 " + lpparam.processName);
//                        XposedBridge.log(getMethodStack());
//                        super.afterHookedMethod(param);
//                    }
//                }
//        );


        XposedHelpers.findAndHookMethod(
                "android.app.ApplicationPackageManager",
                lpparam.classLoader,
                "getInstalledPackages",
                int.class,
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) {
                        XposedBridge.log("调用getInstalledPackages获取了应用安装列表 " + param.args[0]);
                    }

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        XposedBridge.log("调用getInstalledPackages获取了应用安装列表完成 " + lpparam.processName);
                        XposedBridge.log(getMethodStack());
                        super.afterHookedMethod(param);
                    }
                }
        );

//        XposedHelpers.findAndHookMethod(
//                "android.app.ApplicationPackageManager",
//                lpparam.classLoader,
//                "getInstalledPackagesAsUser",
//                int.class,
//                int.class,
//                new XC_MethodHook() {
//                    @Override
//                    protected void beforeHookedMethod(MethodHookParam param) {
//                        XposedBridge.log("调用getInstalledPackagesAsUser获取了应用安装列表");
//                    }
//
//                    @Override
//                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                        XposedBridge.log("调用getInstalledPackagesAsUser获取了应用安装列表完成 " + lpparam.processName);
//                        XposedBridge.log(getMethodStack());
//                        super.afterHookedMethod(param);
//                    }
//                }
//        );

        XposedHelpers.findAndHookMethod(
                "android.app.ApplicationPackageManager",
                lpparam.classLoader,
                "isInstantApp",
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) {
                        XposedBridge.log("调用了isInstantApp");
                    }

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        XposedBridge.log("调用了isInstantApp完成 " + lpparam.processName);
                        XposedBridge.log(getMethodStack());
                        super.afterHookedMethod(param);
                    }
                }
        );

        XposedHelpers.findAndHookMethod(
                "android.app.ApplicationPackageManager",
                lpparam.classLoader,
                "getInstantApps",
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) {
                        XposedBridge.log("调用了getInstantApps");
                    }

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        XposedBridge.log("调用了getInstantApps完成 " + lpparam.processName);
                        XposedBridge.log(getMethodStack());
                        super.afterHookedMethod(param);
                    }
                }
        );

        XposedHelpers.findAndHookMethod(
                "android.app.ApplicationPackageManager",
                lpparam.classLoader,
                "getPackageInfo",
                String.class,
                int.class,
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) {
                        XposedBridge.log("调用了getPackageInfo " + param.args[0]);
                    }

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        XposedBridge.log("调用了getPackageInfo完成 " + lpparam.processName);
                        XposedBridge.log(getMethodStack());
                        super.afterHookedMethod(param);
                    }
                }
        );

//        XposedHelpers.findAndHookMethod(
//                "android.app.ApplicationPackageManager",
//                lpparam.classLoader,
//                "getPackageInfoAsUser",
//                String.class,
//                int.class,
//                int.class,
//                new XC_MethodHook() {
//                    @Override
//                    protected void beforeHookedMethod(MethodHookParam param) {
//                        XposedBridge.log("调用了getPackageInfoAsUser");
//                    }
//
//                    @Override
//                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                        XposedBridge.log("调用了getPackageInfoAsUser完成 " + lpparam.processName);
//                        XposedBridge.log(getMethodStack());
//                        super.afterHookedMethod(param);
//                    }
//                }
//        );

        XposedHelpers.findAndHookMethod(
                "android.app.ActivityManager",
                lpparam.classLoader,
                "getRunningAppProcesses",
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) {
                        XposedBridge.log("调用getRunningAppProcesses获取了应用运行列表");
                    }

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        XposedBridge.log("调用getRunningAppProcesses获取了应用运行列表完成  " + lpparam.processName);
                        XposedBridge.log(getMethodStack());
                        super.afterHookedMethod(param);
                    }
                }
        );

        XposedHelpers.findAndHookMethod(
                ClipboardManager.class.getName(),
                lpparam.classLoader,
                "getPrimaryClip",
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) {
                        XposedBridge.log("调用getPrimaryClip获取了剪切板信息");
                    }

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        XposedBridge.log("调用getPrimaryClip获取了剪切板信息完成 " + lpparam.processName);
                        XposedBridge.log(getMethodStack());
                        super.afterHookedMethod(param);
                    }
                }
        );

        XposedHelpers.findAndHookMethod(
                ClipboardManager.class.getName(),
                lpparam.classLoader,
                "addPrimaryClipChangedListener",
                ClipboardManager.OnPrimaryClipChangedListener.class,
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) {
                        XposedBridge.log("调用addPrimaryClipChangedListener监听了剪切板信息");
                    }

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        XposedBridge.log("调用addPrimaryClipChangedListener监听了剪切板信息完成 " + lpparam.processName);
                        XposedBridge.log(getMethodStack());
                        super.afterHookedMethod(param);
                    }
                }
        );

        XposedHelpers.findAndHookMethod(
                TelephonyManager.class.getName(),
                lpparam.classLoader,
                "getSimSerialNumber",
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) {
                        XposedBridge.log("调用getSimSerialNumber获取了SIM卡序列号");
                    }

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        XposedBridge.log("调用getSimSerialNumber获取了SIM卡序列号完成 " + lpparam.processName);
                        XposedBridge.log(getMethodStack());
                        super.afterHookedMethod(param);
                    }
                }
        );

        XposedHelpers.findAndHookMethod(
                Build.class.getName(),
                lpparam.classLoader,
                "getSerial",
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) {
                        XposedBridge.log("调用getSerial获取了设备序列号");
                    }

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        XposedBridge.log("调用getSerial获取了设备序列号完成 " + lpparam.processName);
                        XposedBridge.log(getMethodStack());
                        super.afterHookedMethod(param);
                    }
                }
        );
    }

    private String getMethodStack() {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();

        StringBuilder stringBuilder = new StringBuilder();

        for (StackTraceElement temp : stackTraceElements) {
            stringBuilder.append(temp.toString()).append("\n");
        }

        return stringBuilder.toString();
    }
}
