package india.covid19.tracker.presentation.ui.statewisereport

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import india.covid19.tracker.domain.entity.Entity
import india.covid19.tracker.presentation.R
import india.covid19.tracker.presentation.common.inflate
import india.covid19.tracker.presentation.ui.home.formatDate
import kotlinx.android.synthetic.main.item_statewise_report.view.*
import org.ocpsoft.prettytime.PrettyTime
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class StateWiseAdapter : RecyclerView.Adapter<StateWiseAdapter.ViewHolder>() {
    private val data: MutableList<Entity.StateWise> = ArrayList()

    private var prettyTime: PrettyTime = PrettyTime(Locale.getDefault())
    private var sdf: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView = parent.inflate(R.layout.item_statewise_report, false)
        return ViewHolder(inflatedView, sdf, prettyTime)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemId(position: Int): Long {
        return data[position].state.hashCode().toLong()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val menuItem = data[position]
        holder.bindItem(menuItem)
    }

    class ViewHolder(itemView: View, private var sdf: SimpleDateFormat,
                     private var prettyTime: PrettyTime) : RecyclerView.ViewHolder(itemView) {
        private var view: View = itemView

        fun bindItem(stateWise: Entity.StateWise) {
            view.textview_state.text = stateWise.state
            view.textview_updated_ago.text = getPrettyTime(stateWise.lastUpdatedTime.formatDate())
            view.textview_confirmed.text = stateWise.confirmed.toString()
            view.textview_active.text = stateWise.active.toString()
            view.textview_recovered.text = stateWise.recovered.toString()
            view.textview_deceased.text = stateWise.deaths.toString()

            if (stateWise.deltaConfirmed > 0) {
                view.textview_delta_confirmed.visibility = View.VISIBLE
                view.textview_delta_confirmed.text = stateWise.deltaConfirmed.toString()
            } else {
                view.textview_delta_confirmed.visibility = View.GONE
            }
            if (stateWise.deltaRecovered > 0) {
                view.textview_delta_recovered.visibility = View.VISIBLE
                view.textview_delta_recovered.text = stateWise.deltaRecovered.toString()
            } else {
                view.textview_delta_recovered.visibility = View.GONE
            }
            if (stateWise.deltaDeaths > 0) {
                view.textview_delta_deceased.visibility = View.VISIBLE
                view.textview_delta_deceased.text = stateWise.deltaDeaths.toString()
            } else {
                view.textview_delta_deceased.visibility = View.GONE
            }
        }

        private fun getPrettyTime(date: String): String {
            val timeInMillis: Long = sdf.parse(date)!!.time
            val formated = prettyTime.format(Date(timeInMillis))
            return formated
        }
    }

    fun updateData(newData: List<Entity.StateWise>) {
        val diffResult = DiffUtil.calculateDiff(
            DiffCallback(
                ArrayList(newData),
                data
            )
        )
        diffResult.dispatchUpdatesTo(this)
        data.clear()
        data.addAll(newData)
    }
}

class DiffCallback(private val newData: MutableList<Entity.StateWise>, private val oldData: List<Entity.StateWise>) : DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldData[oldItemPosition].stateCode == newData[newItemPosition].stateCode
    }

    override fun getOldListSize(): Int {
        return oldData.size
    }

    override fun getNewListSize(): Int {
        return newData.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldData[oldItemPosition] == newData[newItemPosition]
    }
}