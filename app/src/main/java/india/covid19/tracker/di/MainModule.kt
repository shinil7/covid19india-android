package india.covid19.tracker.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import india.covid19.tracker.presentation.ui.home.HomeActivity
import india.covid19.tracker.presentation.ui.home.HomeFragment
import india.covid19.tracker.presentation.ui.launcher.LauncherActivity
import india.covid19.tracker.presentation.ui.statewisereport.StateWiseFragment

/**
 * @author shinilms
 */

@Module
abstract class MainModule {
    @ContributesAndroidInjector
    abstract fun launcherActivity(): LauncherActivity

    @ContributesAndroidInjector
    abstract fun homeActivity(): HomeActivity

    @ContributesAndroidInjector
    abstract fun homeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun stateWiseFragment(): StateWiseFragment
}