package india.covid19.tracker.di

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import india.covid19.tracker.MainApplication
import javax.inject.Singleton

/**
 * @author shinilms
 */

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ViewModelModule::class,
    AppModule::class,
    MainModule::class,
    CloudModule::class,
    UseCaseModule::class])
interface AppComponent : AndroidInjector<MainApplication> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<MainApplication>()
}