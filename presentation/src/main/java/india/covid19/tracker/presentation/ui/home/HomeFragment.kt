package india.covid19.tracker.presentation.ui.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import india.covid19.tracker.domain.entity.Entity
import india.covid19.tracker.presentation.R
import india.covid19.tracker.presentation.ui.base.BaseFragment
import india.covid19.tracker.presentation.ui.home.HomeFragment.Companion.months
import kotlinx.android.synthetic.main.fragment_home.view.*
import org.ocpsoft.prettytime.PrettyTime
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

/**
 * Created by shinilms
 */

const val total_code = "TT"

class HomeFragment : BaseFragment(), View.OnClickListener {
    companion object {
        val months = arrayOf("Jan","Feb","Mar","Apr","May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec")

        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: HomeViewModel by lazy {
        ViewModelProviders.of(activity!!, viewModelFactory).get(HomeViewModel::class.java)
    }

    private lateinit var prettyTime: PrettyTime
    private lateinit var sdf: SimpleDateFormat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        prettyTime = PrettyTime(Locale.getDefault())
        sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, null)

        viewModel.dataLiveData.observe(this, androidx.lifecycle.Observer { list ->
            val totalData = getTotal(list)

            view.text_view_last_updated_time.text = totalData.lastUpdatedTime.formatDateAbsolute()
            view.text_view_last_updated_ago.text = getPrettyTime(totalData.lastUpdatedTime.formatDate()).appendBrackets()

            view.textview_delta_confirmed.text = totalData.deltaConfirmed.appendBrackets()
            view.textview_delta_recovered.text = totalData.deltaRecovered.appendBrackets()
            view.textview_delta_deceased.text = totalData.deltaDeaths.appendBrackets()

            view.textview_confirmed.text = totalData.confirmed.toString()
            view.textview_active.text = totalData.active.toString()
            view.textview_recovered.text = totalData.recovered.toString()
            view.textview_deceased.text = totalData.deaths.toString()
        })

        view.imageview_github.setOnClickListener(this)

        return view
    }

    override fun onClick(p0: View?) {
        if (p0?.id == R.id.imageview_github) {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/shinilms/covid19india-android"))
            startActivity(browserIntent)
        }
    }

    private fun getTotal(stateWiseList: List<Entity.StateWise>): Entity.StateWise {
        for (stateWise in stateWiseList) {
            if (stateWise.stateCode == total_code) {
                return stateWise
            }
        }
        return Entity.StateWise(
            0,
            0,
            0,
            0,
            0,
            0,
            "",
            0,
            "",
            ""
        )
    }

    private fun getPrettyTime(date: String): String {
        val timeInMillis: Long = sdf.parse(date)!!.time
        val formated = prettyTime.format(Date(timeInMillis))
        return formated
    }
}

private fun Int.appendBrackets(): String {
    return "[+$this]"
}

private fun String.appendBrackets(): String {
    return "($this)"
}

fun String.formatDate(): String {
    val day = this.substring(0, 2)
    val month = this.substring(3, 5)
    val year = this.substring(6, 10)
    val time = this.substring(11)
    return year+"-"+month+"-"+day+"T"+time+"Z"
}

private fun String.formatDateAbsolute(): String {
    val day = this.substring(0, 2)
    val month = this.substring(3, 5).toInt()
    val time = this.substring(11, 16)
    return "$day ${months[month-1]}, $time IST"
}