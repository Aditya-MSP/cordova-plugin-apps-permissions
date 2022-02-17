package org.digivogue.appspermissions;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;


import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
 
import java.util.List;

public class AppsPermissions extends CordovaPlugin {
	public static final String TAG = "AppsPermissions";
	@Override
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		if (action.equals("getAppList")) {
			callbackContext.success(this.getAppList());
		}else {
			return false;
		}
		return true;
	}
	private ArrayList getAppList() {
		ArrayList<JSONObject> res = new ArrayList<JSONObject>();    
		try {    
			List<PackageInfo> packs = getPackageManager().getInstalledPackages(0);
			for(int i=0;i<packs.size();i++) {
				PackageInfo p = packs.get(i);
				if ((!getSysPackages) && (p.versionName == null)) {
					continue ;
				}
				JSONObject obj = new JSONObject();
				obj.put("appname", p.applicationInfo.loadLabel(getPackageManager()).toString());
				obj.put("pname", p.packageName);
				obj.put("versionName", p.versionName);
				obj.put("versionCode", p.versionCode);
				obj.put("icon", p.applicationInfo.loadIcon(getPackageManager()));
				res.add(obj);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return res; 
	}

}

