package com.cc.dc.kotlindemo

import android.app.Activity
import android.app.Application
import com.trello.rxlifecycle2.LifecycleProvider
import com.trello.rxlifecycle2.android.ActivityEvent
import java.util.*

/**
 * Created by dc on 2018/5/29.
 * Application
 */
class KotlinApplication : Application() {


    private var appHelper: ApplicationHelper? = null

    override fun onCreate() {
        super.onCreate()
        KotlinApplication.application = this
        if (null == appHelper) {
            appHelper = ApplicationHelper(this)
        }
        init()
    }

    private fun init() {
    }

    companion object {
        val mActivities: MutableList<Activity> = Collections.synchronizedList(LinkedList())

        var application: KotlinApplication? = null

        fun getInstance(): KotlinApplication {
            return application!!
        }

        fun getTopActivity(): Activity? {
            synchronized(this) {
                return mActivities.lastOrNull()
            }
        }

        fun getLifecycleProvider(): LifecycleProvider<ActivityEvent> {
            var activity = mActivities.lastOrNull()!!
            var provider = activity as LifecycleProvider<ActivityEvent>
            return provider
        }
    }
}