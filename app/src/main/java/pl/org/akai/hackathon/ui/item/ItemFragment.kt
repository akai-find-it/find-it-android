package pl.org.akai.hackathon.ui.item

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pl.org.akai.hackathon.databinding.ItemFragmentBinding
import pl.org.akai.hackathon.ui.base.BaseFragment
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class ItemFragment : BaseFragment<ItemFragmentBinding>(ItemFragmentBinding::inflate) {

	override val vm: ItemViewModel by viewModels()
	private val args: ItemFragmentArgs by navArgs()

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		vm.id = args.id

		lifecycleScope.launch(Dispatchers.IO) {
			vm.loadData()
		}

		vm.data.observe(viewLifecycleOwner) {
			it ?: return@observe
			b.dateText.text = "{cmd-calendar} Znaleziono dnia ${it.foundAt.format(DateTimeFormatter.ISO_DATE)}"
			b.founderText.text = "{cmd-account} ${it.founder.firstName} ${it.founder.lastName}"
		}
	}
}
