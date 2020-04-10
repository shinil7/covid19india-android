package india.covid19.tracker.domain.usecase.cloud

import india.covid19.tracker.domain.executor.PostExecutionThread
import india.covid19.tracker.domain.entity.Entity
import india.covid19.tracker.domain.repository.CloudRepository
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

/**
 * @author shinilms
 */

class CloudUseCaseImpl(
    private val postExecutionThread: PostExecutionThread,
    private val repository: CloudRepository
) : CloudUseCase {
    override fun getData(): Single<Entity.Data> {
        return repository.getData()
            .subscribeOn(Schedulers.io())
            .observeOn(postExecutionThread.scheduler)
    }
}