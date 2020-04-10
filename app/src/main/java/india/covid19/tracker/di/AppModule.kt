package india.covid19.tracker.di

import android.content.Context
import dagger.Binds
import dagger.Module
import india.covid19.tracker.MainApplication

/**
 * @author shinilms
 */

@Module(includes = [NetModule::class])
abstract class AppModule {
    @Binds
    abstract fun provideApplicationContext(application: MainApplication): Context
}