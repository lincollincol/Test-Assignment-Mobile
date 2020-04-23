package linc.com.jsonnavigator.utils

import androidx.annotation.IdRes
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class ScreenNavigator(
    private val fragmentManager: FragmentManager,
    @IdRes private val container: Int
) {

    fun navigateToFragment(fragment: Fragment, withBackStack: Boolean = false) {
        val transaction = fragmentManager
            .beginTransaction()
            .replace(container, fragment)
        if(withBackStack) transaction.addToBackStack(null)
        transaction.commit()
    }

    fun popBackStack() {
        fragmentManager.popBackStack()
    }

}