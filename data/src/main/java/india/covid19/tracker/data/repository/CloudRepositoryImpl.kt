package india.covid19.tracker.data.repository

import india.covid19.tracker.data.cloud.CloudService
import india.covid19.tracker.data.mapper.map
import india.covid19.tracker.domain.entity.Entity
import india.covid19.tracker.domain.repository.CloudRepository
import io.reactivex.Single

/**
 * @author shinilms
 */

class CloudRepositoryImpl(private val cloudService: CloudService) : CloudRepository {
    override fun getData(): Single<Entity.Data> {
        return cloudService.getData()
            .map { item -> item.map() }
    }
}
