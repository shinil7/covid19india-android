package india.covid19.tracker.presentation.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import india.covid19.tracker.presentation.R
import india.covid19.tracker.presentation.ui.base.BaseActivity
import india.covid19.tracker.presentation.ui.common.BottomBarAdapter
import india.covid19.tracker.presentation.ui.statewisereport.StateWiseFragment
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject

/**
 * @author shinilms
 */

class HomeActivity : BaseActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: HomeViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel::class.java)
    }

    private lateinit var viewPager: ViewPager
    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                viewPager.setCurrentItem(0, false)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_statewise -> {
                viewPager.setCurrentItem(1, false)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        initBottomBar()

        viewModel.loadErrorLiveData.observe(this, Observer { loadError ->
            handleLoadError(loadError)
        })

        loadData()
    }

    private fun initBottomBar() {
        val pagerAdapter =
            BottomBarAdapter(
                supportFragmentManager
            )
        pagerAdapter.addFragments(HomeFragment.newInstance())
        pagerAdapter.addFragments(StateWiseFragment.newInstance())

        viewPager = findViewById(R.id.viewpager)
        viewPager.adapter = pagerAdapter

        nav_view.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }

    private fun handleLoadError(loadError: Boolean?) {
        if (loadError!!) {
            Snackbar.make(container, "Failed to load data", Snackbar.LENGTH_INDEFINITE)
                .setAction("Retry") { loadData() }
                .show()
        }
    }

    private fun loadData() {
        viewModel.getData()
    }

    companion object {
        fun open(context: Context) {
            val intent = Intent(context, HomeActivity::class.java)
            context.startActivity(intent)
        }
    }
}
