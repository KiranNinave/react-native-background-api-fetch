package com.backgroundnetworkrequest;

import android.app.Activity;
import android.content.Intent;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.WritableMap;

public class ReactNativeNetworkApi extends ReactContextBaseJavaModule implements FetchDataCallbackInterface {
    Promise myPromise;

    public ReactNativeNetworkApi(ReactApplicationContext reactContext){
        super(reactContext);
    }


    @ReactMethod
    public void request(String url, Promise promise) {
            Activity currentactivity = getCurrentActivity();
            if(currentactivity== null){
                promise.reject("error", "no activity");
                return;
            }
            myPromise = promise;
            try{
                new FetchData(url, this).execute();
            }catch (Exception e){
                promise.reject(e);
            }
    }


    @Override
    public String getName() {
        return "ReactNativeNetworkApi";
    }

    @Override
    public void fetchDataCallback(Object result) {
        if(myPromise != null){
            WritableMap map = Arguments.createMap();
            map.putString("response", result.toString());
            myPromise.resolve(map);
        }
    }
}
