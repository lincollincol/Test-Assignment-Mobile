package linc.com.jsonnavigator.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.Fragment
import linc.com.jsonnavigator.R
import linc.com.jsonnavigator.ui.folder.FolderContentFragment
import linc.com.jsonnavigator.utils.Constants.Companion.NOTHING
import linc.com.jsonnavigator.utils.PathFormatter
import linc.com.jsonnavigator.utils.ScreenNavigator

class MainActivity : AppCompatActivity(), NavigatorActivity {

    private lateinit var screenNavigator: ScreenNavigator
    private lateinit var pathFormatter: PathFormatter
    private lateinit var path: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        path = findViewById(R.id.path)
        pathFormatter = PathFormatter()

        screenNavigator = ScreenNavigator(supportFragmentManager, R.id.container)
        navigateToFragment(
            FolderContentFragment.newInstance(Bundle.EMPTY),
            false,
            NOTHING)
    }

    override fun navigateToFragment(fragment: Fragment, withBackStack: Boolean, name: String) {
        pathFormatter.addNew(name)
        screenNavigator.navigateToFragment(fragment, withBackStack)
        path.text = pathFormatter.getPath()
    }

    override fun popBackStack() {
        screenNavigator.popBackStack()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        pathFormatter.removeLast()
        path.text = pathFormatter.getPath()
    }

}
