package india.covid19.tracker.data.mapper

import india.covid19.tracker.data.cloud.CloudEntity
import india.covid19.tracker.domain.entity.Entity

/**
 * @author shinilms
 */

fun CloudEntity.Data.map() = Entity.Data(
        casesTimeSeries = getMappedCasesTimeSeries(casesTimeSeries),
        stateWise = getMappedStateWise(stateWise),
        tested = getMappedTested(tested)
)

private fun getMappedCasesTimeSeries(series: List<CloudEntity.CasesTimeSeries>): List<Entity.CasesTimeSeries> {
        val list = ArrayList<Entity.CasesTimeSeries>()
        for (s in series) {
                list.add(s.map())
        }
        return list
}

private fun getMappedStateWise(series: List<CloudEntity.StateWise>): List<Entity.StateWise> {
        val list = ArrayList<Entity.StateWise>()
        for (s in series) {
                list.add(s.map())
        }
        return list
}

private fun getMappedTested(series: List<CloudEntity.Tested>): List<Entity.Tested> {
        val list = ArrayList<Entity.Tested>()
        for (s in series) {
                list.add(s.map())
        }
        return list
}

fun CloudEntity.CasesTimeSeries.map() = Entity.CasesTimeSeries(
        dailyConfirmed = dailyConfirmed,
        dailyDeceased = dailyDeceased,
        dailyRecovered = dailyRecovered,
        date = date,
        totalConfirmed = totalConfirmed,
        totalDeceased = totalDeceased,
        totalRecovered = totalRecovered
)

fun CloudEntity.StateWise.map() = Entity.StateWise(
        active = active,
        confirmed = confirmed,
        deaths = deaths,
        deltaConfirmed = deltaConfirmed,
        deltaDeaths = deltaDeaths,
        deltaRecovered = deltaRecovered,
        lastUpdatedTime = lastUpdatedTime,
        recovered = recovered,
        state = state,
        stateCode = stateCode
)

fun CloudEntity.Tested.map() = Entity.Tested(
        _ckd7g = _ckd7g,
        source = source,
        testsConductedByPrivateLabs = testsConductedByPrivateLabs,
        totalIndividualsTested = totalIndividualsTested,
        totalPositiveCases = totalPositiveCases,
        totalSamplesTested = totalSamplesTested,
        updateTimestamp = updateTimestamp
)