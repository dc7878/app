package com.cc.dc.kotlindemo

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.cc.dc.kotlindemo.KotlinApplication.Companion.mActivities

/**
 * Created by dc on 2018/6/15.
 */
class ApplicationHelper {

    private var application: KotlinApplication? = null

    constructor(application: KotlinApplication?) {
        this.application = application

        init()
    }

    private fun init() {
        initActivityHelper()
    }

    private fun initActivityHelper() {
        application!!.registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
            override fun onActivityPaused(activity: Activity?) {
            }

            override fun onActivityResumed(activity: Activity?) {
            }

            override fun onActivityStarted(activity: Activity?) {
            }

            override fun onActivityDestroyed(activity: Activity?) {
                popActivity(activity!!)
            }

            override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
            }

            override fun onActivityStopped(activity: Activity?) {
            }

            override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
                pushActivity(activity!!)
            }
        })
    }

    private fun pushActivity(activity: Activity): Boolean {
        synchronized(mActivities) {
            return mActivities.add(activity)
        }
    }

    private fun popActivity(activity: Activity?): Boolean {
        synchronized(mActivities) {
            return activity != null && mActivities.remove(activity)
        }
    }
}