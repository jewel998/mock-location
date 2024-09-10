package com.dailydumbbell.plugin.mocklocation;

import android.util.Log;

import com.getcapacitor.JSArray;
import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import org.json.JSONException;

@CapacitorPlugin(name = "MockLocation")
public class MockLocationPlugin extends Plugin {
    private MockLocation implementation = new MockLocation();

    @PluginMethod
    public void isMockLocation(PluginCall call) {
        JSObject ret = new JSObject();
        JSArray whitelist = call.getArray("whitelist", new JSArray());
        try {
            ret.put("isEnabled", implementation.isMockLocation(
                    getActivity(),
                    whitelist.<String>toList())
            );
            ret.put("packages", implementation.apps);
        } catch (JSONException e) {
            Log.e("MockLocation", "Need to add whitelist apps list", e);
        }
        call.resolve(ret);
    }

    @PluginMethod
    public void isDevOptionsEnabled(PluginCall call) {
        JSObject ret = new JSObject();
        ret.put("isEnabled", implementation.isDevOptionsEnabled(getActivity()));
        call.resolve(ret);
    }
}
