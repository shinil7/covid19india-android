package india.covid19.tracker

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import india.covid19.tracker.di.DaggerAppComponent
import timber.log.Timber

/**
 * @author shinilms
 */

class MainApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().create(this)
    }

    override fun onCreate() {
        super.onCreate()
        initTimber()
    }

    private fun initTimber() {
        Timber.plant(object : Timber.DebugTree() {
            override fun createStackElementTag(e: StackTraceElement): String {
                return "(${e.fileName}:${e.lineNumber}) ${e.methodName}()"
            }
        })
        Timber.d("initTimber")
    }
}