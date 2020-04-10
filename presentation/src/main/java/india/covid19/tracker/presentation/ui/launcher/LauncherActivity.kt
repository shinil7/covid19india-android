package india.covid19.tracker.presentation.ui.launcher

import android.os.Bundle
import india.covid19.tracker.presentation.ui.base.BaseActivity
import india.covid19.tracker.presentation.ui.home.HomeActivity

/**
 * @author shinilms
 */

class LauncherActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        HomeActivity.open(applicationContext)
        finish()
    }
}