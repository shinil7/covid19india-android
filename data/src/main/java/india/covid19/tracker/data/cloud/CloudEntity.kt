package india.covid19.tracker.data.cloud

import com.google.gson.annotations.SerializedName

/**
 * @author shinilms
 */

sealed class CloudEntity {
        data class Data(
                @SerializedName("cases_time_series") val casesTimeSeries : List<CasesTimeSeries>,
                @SerializedName("statewise") val stateWise : List<StateWise>,
                @SerializedName("tested") val tested : List<Tested>
        ) : CloudEntity()

        data class CasesTimeSeries(
                @SerializedName("dailyconfirmed") val dailyConfirmed : Int,
                @SerializedName("dailydeceased") val dailyDeceased : Int,
                @SerializedName("dailyrecovered") val dailyRecovered : Int,
                @SerializedName("date") val date : String,
                @SerializedName("totalconfirmed") val totalConfirmed : Int,
                @SerializedName("totaldeceased") val totalDeceased : Int,
                @SerializedName("totalrecovered") val totalRecovered : Int
        ) : CloudEntity()

        data class StateWise(
                @SerializedName("active") val active : Int,
                @SerializedName("confirmed") val confirmed : Int,
                @SerializedName("deaths") val deaths : Int,
                @SerializedName("deltaconfirmed") val deltaConfirmed : Int,
                @SerializedName("deltadeaths") val deltaDeaths : Int,
                @SerializedName("deltarecovered") val deltaRecovered : Int,
                @SerializedName("lastupdatedtime") val lastUpdatedTime : String,
                @SerializedName("recovered") val recovered : Int,
                @SerializedName("state") val state : String,
                @SerializedName("statecode") val stateCode : String
        ) : CloudEntity()

        data class Tested(
                @SerializedName("_ckd7g") val _ckd7g : Int,
                @SerializedName("source") val source : String,
                @SerializedName("testsconductedbyprivatelabs") val testsConductedByPrivateLabs : String,
                @SerializedName("totalindividualstested") val totalIndividualsTested : Int,
                @SerializedName("totalpositivecases") val totalPositiveCases : Int,
                @SerializedName("totalsamplestested") val totalSamplesTested : Int,
                @SerializedName("updatetimestamp") val updateTimestamp : String
        ) : CloudEntity()
}