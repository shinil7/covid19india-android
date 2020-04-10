package india.covid19.tracker.di

import dagger.Module
import dagger.Provides
import india.covid19.tracker.UIThread
import india.covid19.tracker.domain.repository.CloudRepository
import india.covid19.tracker.domain.usecase.cloud.CloudUseCase
import india.covid19.tracker.domain.usecase.cloud.CloudUseCaseImpl

/**
 * @author shinilms
 */

@Module
class UseCaseModule {
    @Provides
    fun provideCloudUseCase(repository: CloudRepository): CloudUseCase = CloudUseCaseImpl(UIThread(), repository)
}
