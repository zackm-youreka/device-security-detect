package com.mukha.andrei.plugins.device.secutiry.detect;

import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class DeviceSecurityDetect {
    public boolean isDeviceRooted() {
        return checkBuildProperties() || checkSuBinary() || isSuBinaryAvailable();
    }

    private boolean checkBuildProperties() {
        return containsSubstring(android.os.Build.TAGS, "test-keys") ||
               containsSubstring(android.os.Build.FINGERPRINT, "test-keys") ||
               containsSubstring(android.os.Build.DISPLAY, "test-keys") ||
               containsSubstring(android.os.Build.HARDWARE, "goldfish") ||
               containsSubstring(android.os.Build.PRODUCT, "sdk") ||
               containsSubstring(android.os.Build.PRODUCT, "generic");
    }

    private boolean containsSubstring(String propertyValue, String substring) {
        return propertyValue != null && propertyValue.contains(substring);
    }

    private boolean checkSuBinary() {
        String[] paths = {
                "/system/app/Superuser.apk",
                "/sbin/su", "/system/bin/su",
                "/system/xbin/su",
                "/data/local/xbin/su",
                "/data/local/bin/su",
                "/system/sd/xbin/su",
                "/system/bin/failsafe/su",
                "/data/local/su",
                "/su/bin/su"
        };
        for (String path : paths) {
            if (new File(path).exists()) return true;
        }
        return false;
    }

    private boolean isSuBinaryAvailable() {
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(new String[] { "/system/xbin/which", "su" });
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            if (in.readLine() != null) return true;
            return false;
        } catch (Throwable t) {
            return false;
        } finally {
            if (process != null) process.destroy();
        }
    }
}
