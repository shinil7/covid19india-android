package india.covid19.tracker.data.cloud

import io.reactivex.Single
import io.reactivex.annotations.CheckReturnValue
import retrofit2.http.GET

/**
 * @author shinilms
 */

interface CloudService {
    @GET("data.json")
    @CheckReturnValue
    fun getData(): Single<CloudEntity.Data>
}