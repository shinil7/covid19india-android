package india.covid19.tracker.domain.executor

import io.reactivex.Scheduler

/**
 * @author shinilms
 */

interface PostExecutionThread {
    val scheduler: Scheduler
}
