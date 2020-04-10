package india.covid19.tracker.presentation.ui.statewisereport

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import india.covid19.tracker.domain.entity.Entity
import india.covid19.tracker.presentation.R
import india.covid19.tracker.presentation.ui.base.BaseFragment
import india.covid19.tracker.presentation.ui.home.HomeViewModel
import india.covid19.tracker.presentation.ui.home.total_code
import kotlinx.android.synthetic.main.fragment_statewise.view.*
import javax.inject.Inject

/**
 * @author shinilms
 */

class StateWiseFragment : BaseFragment() {
    companion object {
        fun newInstance(): StateWiseFragment {
            return StateWiseFragment()
        }
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: HomeViewModel by lazy {
        ViewModelProviders.of(activity!!, viewModelFactory).get(HomeViewModel::class.java)
    }

    private val adapter: StateWiseAdapter by lazy {
        StateWiseAdapter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_statewise, null)

        view.recyclerview_statewise.setHasFixedSize(true)
        view.recyclerview_statewise.addItemDecoration(DividerItemDecoration(activity?.applicationContext, DividerItemDecoration.VERTICAL))
        adapter.setHasStableIds(true)
        view.recyclerview_statewise.adapter = adapter

        viewModel.dataLiveData.observe(this, androidx.lifecycle.Observer { list ->
            adapter.updateData(filterOutTotal(list))
        })

        return view
    }

    private fun filterOutTotal(stateWise: List<Entity.StateWise>): List<Entity.StateWise> {
        val list = ArrayList<Entity.StateWise>()
        for (state in stateWise) {
            if (state.stateCode != total_code) {
                list.add(state)
            }
        }
        return list
    }
}