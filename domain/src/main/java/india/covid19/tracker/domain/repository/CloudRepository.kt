package india.covid19.tracker.domain.repository

import india.covid19.tracker.domain.entity.Entity
import io.reactivex.Single

/**
 * @author shinilms
 */

interface CloudRepository {
    fun getData(): Single<Entity.Data>
}