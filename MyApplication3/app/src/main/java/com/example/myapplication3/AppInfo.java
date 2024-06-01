package com.example.myapplication3;

import android.graphics.drawable.Drawable;

public class AppInfo {
    private String appName;
    private String appPackage;
    private Drawable appIcon;

    public AppInfo() {
    }
    public AppInfo(String appName, String appPackage, Drawable appIcon) {
        this.appName = appName;
        this.appPackage = appPackage;
        this.appIcon = appIcon;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppPackage() {
        return appPackage;
    }

    public void setAppPackage(String appPackage) {
        this.appPackage = appPackage;
    }

    public Drawable getAppIcon() {
        return appIcon;
    }

    public void setAppIcon(Drawable appIcon) {
        this.appIcon = appIcon;
    }
}
