package com.dailydumbbell.plugin.mocklocation;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;
import org.json.JSONArray;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MockLocation {
    public JSONArray apps = new JSONArray();

    public boolean isMockLocation(Activity activity, List<String> whitelist) {
        boolean isMock;

        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) {
            if (Settings.Secure.getString(activity.getContentResolver(), Settings.Secure.ALLOW_MOCK_LOCATION) != "0") {
                isMock = true;
            } else {
                isMock = false;
            }
        } else {
            ArrayList<String> whitelistedApps = new ArrayList<String>();
            whitelistedApps.addAll(whitelist);

            isMock = checkAllMockLocationApps(activity, whitelistedApps);
        }

        return isMock;
    }

    public boolean checkAllMockLocationApps(Activity activity, ArrayList<String> whitelist) {
        int count = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            boolean hasPermission = activity.checkSelfPermission(Manifest.permission.QUERY_ALL_PACKAGES) == PackageManager.PERMISSION_GRANTED;
            if (!hasPermission) {
                activity.requestPermissions(new String[]{Manifest.permission.QUERY_ALL_PACKAGES}, 1);
            } else {
                PackageManager pm = activity.getPackageManager();
                List<PackageInfo> packages = pm.getInstalledPackages(PackageManager.GET_META_DATA);
                final int packagesCount = packages.size();
                for (int i = 0; i < packagesCount; i++) {
                    PackageInfo p = packages.get(i);
                    try {
                        PackageInfo packageInfo = pm.getPackageInfo(
                                p.packageName,
                                PackageManager.GET_PERMISSIONS
                        );

                        String[] requestedPermissions = packageInfo.requestedPermissions;
                        String perm = "android.permission.ACCESS_MOCK_LOCATION";
                        if (requestedPermissions != null && Arrays.stream(requestedPermissions).anyMatch(perm::contains)
                                && packageInfo.packageName != activity.getPackageName()
                                && !whitelist.contains(packageInfo.packageName)
                        ) {
                            count++;
                            apps.put(packageInfo.packageName);
                        }
                    } catch (PackageManager.NameNotFoundException e) {
                        Log.e("MockLocation", "Got exception " + e.getMessage());
                    }
                }
            }
        } else {
            PackageManager pm = activity.getPackageManager();
            List<PackageInfo> packages = pm.getInstalledPackages(PackageManager.GET_META_DATA);
            final int packagesCount = packages.size();
            for (int i = 0; i < packagesCount; i++) {
                PackageInfo p = packages.get(i);
                try {
                    PackageInfo packageInfo = pm.getPackageInfo(
                            p.packageName,
                            PackageManager.GET_PERMISSIONS
                    );

                    ApplicationInfo appInfo = pm.getApplicationInfo(p.packageName,0);

                    // Get Permissions
                    String[] permissions = packageInfo.requestedPermissions;
                    ArrayList<String> requestedPermissions = new ArrayList();
                    if (permissions != null) {  // Check if permissions array is not null
                        final int permissionSize = permissions.length;
                        
                        // Iterate over the permissions and add them to the requestedPermissions list
                        for (int j = 0; j < permissionSize; j++) {
                            requestedPermissions.add(permissions[j]);
                        }
                    }

                    String perm = "android.permission.ACCESS_MOCK_LOCATION";
                    if (requestedPermissions != null && requestedPermissions.contains(perm)
                            && packageInfo.packageName != activity.getPackageName()
                            && appInfo.flags != 0 && ApplicationInfo.FLAG_SYSTEM == 0
                            && !whitelist.contains(packageInfo.packageName)
                    ) {
                        count++;
                        apps.put(packageInfo.packageName);
                    }
                } catch (PackageManager.NameNotFoundException e) {
                    Log.e("MockLocation", "Got exception " + e.getMessage());
                }
            }
        }

        return count > 0;
    }
    
    public boolean isDeveloperOptionsEnabled(Activity activity) {
        Context context = activity.getApplicationContext();
        int devOptions;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            devOptions = Settings.Global.getInt(context.getContentResolver(), Settings.Global.DEVELOPMENT_SETTINGS_ENABLED, 0);
        } else {
            devOptions = Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.DEVELOPMENT_SETTINGS_ENABLED, 0);
        }
        return devOptions != 0;
    }

    public boolean isADBEnabled(Activity activity) {
        Context context = activity.getApplicationContext();
        int adbEnabled;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            adbEnabled = Settings.Global.getInt(context.getContentResolver(), Settings.Global.ADB_ENABLED, 0);
        } else {
            adbEnabled = Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.ADB_ENABLED, 0);
        }
        return adbEnabled != 0;
    }

    public boolean isDevOptionsEnabled(Activity activity) {
        boolean devOptions = isDeveloperOptionsEnabled(activity);
        boolean adbEnabled = isADBEnabled(activity);

        return devOptions || adbEnabled;
    }
}
