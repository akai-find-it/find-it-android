package pl.org.akai.hackathon.ui.add

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pl.org.akai.hackathon.databinding.AddAnswerFragmentBinding
import pl.org.akai.hackathon.ui.base.BaseFragment

@AndroidEntryPoint
class AddAnswerFragment : BaseFragment<AddAnswerFragmentBinding>(AddAnswerFragmentBinding::inflate) {

	override val vm: AddAnswerViewModel by viewModels()
	val args by navArgs<AddAnswerFragmentArgs>()

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		val adapter = AddAnswerAdapter()
		b.list.adapter = adapter
		b.list.layoutManager = LinearLayoutManager(context)
		b.guess = false
		vm.categoryId = args.category

		vm.data.observe(viewLifecycleOwner) {
			adapter.submitList(it)
		}

		vm.navigateBack.observe(viewLifecycleOwner) {
			if (it == true) {
				findNavController().navigate(AddAnswerFragmentDirections.actionAddAnswerFragmentToLostListFragment())
				vm.endNavigateBack()
			}
		}

		b.addButton.setOnClickListener {
			with(args) {
				vm.add(name, description, category, date)
			}
		}

		lifecycleScope.launch(Dispatchers.IO) {
			vm.loadData()
		}
	}
}
