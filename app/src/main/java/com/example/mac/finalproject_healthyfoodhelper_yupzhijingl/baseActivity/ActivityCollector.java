package com.example.mac.finalproject_healthyfoodhelper_yupzhijingl.baseActivity;

import android.app.Activity;

import com.example.mac.finalproject_healthyfoodhelper_yupzhijingl.util.LogUtil;

import java.util.ArrayList;
import java.util.List;




public class ActivityCollector {

    private static final String TAG = "ActivityCollector";

    // the activity lists saving activity to get better management
    private final List<Activity> activities = new ArrayList<>();

    // the latest activity
    private Activity latestActivity;


    // set the constructor to private for single instance
    private ActivityCollector() {}

    // get single instance
    public static ActivityCollector getInstance() {
        return ActivityCollectorHolder.activityCollector;
    }

    // add it into activity list when a new activity is created
    public void addActivity(Activity activity) {
        if(activity != null && !(activities.contains(activity))) {
            activities.add(activity);
            LogUtil.i(TAG, "addActivity: activity name is " + activity.getClass().getSimpleName());
        }
    }

    // remove it from activity list when a old activity is destroyed
    public void removeActivity(Activity activity) {
        if(activity != null && activities.contains(activity)) {
            activities.remove(activity);
            LogUtil.i(TAG, "removeActivity: activity name is " + activity.getClass().getSimpleName());
        }
    }

    // sign out and finish all activity
    public void finishAll() {
        // iterate activity for finish every one
        for (Activity activity : activities) {
            if( !(activity.isFinishing()) ) {
                activity.finish();
            }
        }

        // clear activity list
        activities.clear();
    }

    // the setter for latest activity
    public void setLatestActivity(Activity latestActivity) {
        this.latestActivity = latestActivity;
    }

    // the getter for latest activity
    public Activity getLatestActivity() {
        return latestActivity;
    }


    /**
     * @author bsl
     * @describe the inner-class of ActivityCollector for single instance
     */
    public static class ActivityCollectorHolder {
        private static final ActivityCollector activityCollector = new ActivityCollector();
    }

}
