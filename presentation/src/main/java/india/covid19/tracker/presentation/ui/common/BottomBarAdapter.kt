package india.covid19.tracker.presentation.ui.common

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import india.covid19.tracker.presentation.ui.base.BaseFragmentStatePagerAdapter

/**
 * @author shinilms
 */

class BottomBarAdapter(fragmentManager: FragmentManager) : BaseFragmentStatePagerAdapter<Fragment>(fragmentManager) {
    private val fragments = ArrayList<Fragment>()

    fun addFragments(fragment: Fragment) {
        fragments.add(fragment)
    }

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }
}