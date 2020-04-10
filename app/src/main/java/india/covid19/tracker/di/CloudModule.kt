package india.covid19.tracker.di

import dagger.Module
import dagger.Provides
import india.covid19.tracker.data.cloud.CloudService
import india.covid19.tracker.data.repository.CloudRepositoryImpl
import india.covid19.tracker.domain.repository.CloudRepository
import retrofit2.Retrofit
import javax.inject.Named

/**
 * @author shinilms
 */

@Module
class CloudModule {
    @Provides
    fun provideCloudRepository(cloudService: CloudService): CloudRepository {
        return CloudRepositoryImpl(cloudService)
    }

    @Provides
    fun provideCloudService(@Named("Cloud") retrofit: Retrofit): CloudService {
        return retrofit.create(CloudService::class.java)
    }
}