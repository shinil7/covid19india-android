package india.covid19.tracker.presentation.ui.base

import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.disposables.CompositeDisposable

/**
 * @author shinilms
 */

abstract class BaseActivity : DaggerAppCompatActivity() {
    protected val disposables = CompositeDisposable()

    override fun onBackPressed() {
        if (canPopFragmentBackStack()) {
            supportFragmentManager.popBackStack()
        } else {
            finish()
        }
    }

    fun canPopFragmentBackStack() = supportFragmentManager.backStackEntryCount > 1

    override fun onDestroy() {
        disposables.clear()
        super.onDestroy()
    }
}