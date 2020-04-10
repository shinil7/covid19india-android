package india.covid19.tracker.domain.usecase.cloud

import india.covid19.tracker.domain.entity.Entity
import india.covid19.tracker.domain.usecase.BaseUseCase
import io.reactivex.Single

/**
 * @author shinilms
 */

interface CloudUseCase : BaseUseCase {
    fun getData(): Single<Entity.Data>
}