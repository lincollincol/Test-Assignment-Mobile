package linc.com.jsonnavigator.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import linc.com.jsonnavigator.R
import linc.com.jsonnavigator.data.JsonFilesystemRepository
import linc.com.jsonnavigator.device.AssetReader
import linc.com.jsonnavigator.domain.interactors.FolderContentInteractorImpl
import linc.com.jsonnavigator.ui.folder.FolderContentFragment
import linc.com.jsonnavigator.ui.folder.FolderContentPresenter
import linc.com.jsonnavigator.utils.ScreenNavigator

class MainActivity : AppCompatActivity(), NavigatorActivity {

    private lateinit var screenNavigator: ScreenNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        screenNavigator = ScreenNavigator(supportFragmentManager, R.id.container).apply {
            navigateToFragment(FolderContentFragment.newInstance(Bundle.EMPTY), false)
        }

    }

    override fun navigateToFragment(fragment: Fragment, withBackStack: Boolean) {
        screenNavigator.navigateToFragment(fragment, withBackStack)
    }

    override fun popBackStack() {
        screenNavigator.popBackStack()
    }

}
