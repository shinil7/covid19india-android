package india.covid19.tracker.presentation.ui.base

import dagger.android.support.DaggerFragment
import io.reactivex.disposables.CompositeDisposable

/**
 * @author shinilms
 */

abstract class BaseFragment : DaggerFragment() {
    protected val disposables = CompositeDisposable()

    override fun onDetach() {
        super.onDetach()
        disposables.clear()
    }
}