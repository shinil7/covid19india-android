package india.covid19.tracker.presentation.ui.base

import android.util.SparseArray
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

/*
   Extension of FragmentStatePagerAdapter which intelligently caches
   all active fragments and manages the fragment lifecycles.
*/
@Suppress("UNCHECKED_CAST")
abstract class BaseFragmentStatePagerAdapter<T : Fragment>(
    fragmentManager: FragmentManager
) : FragmentStatePagerAdapter(fragmentManager) {
    private val registeredFragments = SparseArray<T>()

    // Register the fragment when the item is instantiated
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val fragment = super.instantiateItem(container, position) as T
        registeredFragments.put(position, fragment)
        return fragment
    }

    // Unregister when the item is inactive
    override fun destroyItem(
        container: ViewGroup,
        position: Int,
        `object`: Any
    ) {
        registeredFragments.remove(position)
        super.destroyItem(container, position, `object`)
    }

    // Returns the fragment for the position (if instantiated)
    fun getRegisteredFragment(position: Int): T {
        return registeredFragments[position]
    }
}