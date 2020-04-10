package india.covid19.tracker.presentation.ui.home

import androidx.lifecycle.MutableLiveData
import india.covid19.tracker.domain.entity.Entity
import india.covid19.tracker.domain.usecase.cloud.CloudUseCase
import india.covid19.tracker.presentation.common.ConfirmedComparatorDesc
import india.covid19.tracker.presentation.ui.base.BaseViewModel
import timber.log.Timber
import java.util.*
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val cloudUseCase: CloudUseCase
) : BaseViewModel() {

    val dataLiveData = MutableLiveData<List<Entity.StateWise>>()
    val loadErrorLiveData = MutableLiveData<Boolean>()

    fun getData() {
        disposables.add(
            cloudUseCase.getData()
                .map { item ->
                    item.stateWise
                }
                .map {
                    val sortedList = ArrayList(it)
                    Collections.sort(sortedList, ConfirmedComparatorDesc())
                    sortedList
                }
                .subscribe({ stateWiseData ->
                    loadErrorLiveData.postValue(false)
                    dataLiveData.postValue(stateWiseData)
                }, { t ->
                    loadErrorLiveData.postValue(true)
                    Timber.e(t)
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}