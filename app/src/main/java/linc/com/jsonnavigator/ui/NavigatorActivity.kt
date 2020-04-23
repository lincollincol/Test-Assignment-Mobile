package linc.com.jsonnavigator.ui

import androidx.fragment.app.Fragment

interface NavigatorActivity {

    fun navigateToFragment(fragment: Fragment, withBackStack: Boolean)
    fun popBackStack()

}