package pl.org.akai.hackathon.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.forEach
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.mikepenz.iconics.IconicsDrawable
import com.mikepenz.iconics.typeface.IIcon
import com.mikepenz.iconics.typeface.library.community.material.CommunityMaterial
import com.mikepenz.iconics.utils.sizeDp
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import pl.org.akai.hackathon.R
import pl.org.akai.hackathon.databinding.MainActivityBinding
import pl.org.akai.hackathon.ext.viewBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), CoroutineScope {

	override val coroutineContext = Job() + Dispatchers.IO

	private val b by viewBinding(MainActivityBinding::inflate)
	private val vm by viewModels<MainViewModel>()
	private val navController by lazy { findNavController(R.id.nav_host_fragment) }
	private lateinit var appBarConfiguration: AppBarConfiguration

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(b.root)
		setSupportActionBar(b.toolbar)

		b.toolbar.setupWithNavController(navController)
		b.bottomNav.setupWithNavController(navController)
		appBarConfiguration = AppBarConfiguration(navController.graph)
		setupActionBarWithNavController(navController, appBarConfiguration)

		b.bottomNav.menu.forEach {
			val icon: IIcon = when (it.itemId) {
				R.id.lostListFragment -> CommunityMaterial.Icon.cmd_clipboard_list
				R.id.addFragment -> CommunityMaterial.Icon.cmd_comment_plus
				R.id.userFragment -> CommunityMaterial.Icon.cmd_account_heart_outline
				else -> CommunityMaterial.Icon2.cmd_help
			}
			it.icon = IconicsDrawable(this, icon).apply {
				sizeDp = 24
			}
		}

		b.toolbar.menu.forEach {
			val icon: IIcon = when (it.itemId) {
				R.id.filter -> CommunityMaterial.Icon2.cmd_filter_outline
				else -> CommunityMaterial.Icon2.cmd_help
			}
			it.icon = IconicsDrawable(this, icon).apply {
				sizeDp = 24
			}
		}

		vm.title.observe(this) {
//			b.toolbar.title = it
		}
	}

	override fun onSupportNavigateUp(): Boolean {
		return navController.navigateUp() || super.onSupportNavigateUp()
	}
}

