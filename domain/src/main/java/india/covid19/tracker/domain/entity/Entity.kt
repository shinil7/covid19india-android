package india.covid19.tracker.domain.entity

/**
 * @author shinilms
 */

sealed class Entity {
    data class Data(
        val casesTimeSeries : List<CasesTimeSeries>,
        val stateWise : List<StateWise>,
        val tested : List<Tested>
    ) : Entity()

    data class CasesTimeSeries(
        val dailyConfirmed : Int,
        val dailyDeceased : Int,
        val dailyRecovered : Int,
        val date : String,
        val totalConfirmed : Int,
        val totalDeceased : Int,
        val totalRecovered : Int
    ) : Entity()

    data class StateWise(
        val active : Int,
        val confirmed : Int,
        val deaths : Int,
        val deltaConfirmed : Int,
        val deltaDeaths : Int,
        val deltaRecovered : Int,
        val lastUpdatedTime : String,
        val recovered : Int,
        val state : String,
        val stateCode : String
    ) : Entity()

    data class Tested(
        val _ckd7g : Int,
        val source : String,
        val testsConductedByPrivateLabs : String,
        val totalIndividualsTested : Int,
        val totalPositiveCases : Int,
        val totalSamplesTested : Int,
        val updateTimestamp : String
    ) : Entity()
}
