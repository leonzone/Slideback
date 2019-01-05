package com.reiser.slideback;

import android.app.Activity;

import com.reiser.slideback.interfaces.SlideBackCallBack;

import java.util.WeakHashMap;

/**
 * Created by reiserx on 2019/1/5.
 * desc :
 */
public class SlideBack {

    private static WeakHashMap<Activity, SlideBackHelper> mContiner = new WeakHashMap<>();


    public static void register(Activity activity, SlideBackCallBack callBack) {
        SlideBackHelper helper = new SlideBackHelper();
        helper.register(activity, callBack);
        mContiner.put(activity, helper);
    }

    public static void unRegister(Activity activity) {
        SlideBackHelper helper = mContiner.get(activity);
        if (helper != null) {
            helper.unRegister();
            mContiner.remove(activity);
        }
    }
}
