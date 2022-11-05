package pl.org.akai.hackathon.ui.lost

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import pl.org.akai.hackathon.databinding.LostListFragmentBinding
import pl.org.akai.hackathon.ui.base.BaseFragment
import pl.org.akai.hackathon.ui.base.ListLoadStateAdapter

@AndroidEntryPoint
class LostListFragment : BaseFragment<LostListFragmentBinding>(LostListFragmentBinding::inflate) {

	override val vm: LostListViewModel by viewModels()

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		b.vm = vm

		val adapter = LostListAdapter(LostListComparator, vm::onItemClicked)

		b.list.adapter = adapter.withLoadStateHeaderAndFooter(
			header = ListLoadStateAdapter(adapter::retry),
			footer = ListLoadStateAdapter(adapter::retry),
		)
//		b.list.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
		b.list.layoutManager = LinearLayoutManager(context)

		lifecycleScope.launch {
			adapter.loadStateFlow.collectLatest { loadStates ->
				b.progressBar.isVisible = loadStates.refresh is LoadState.Loading
				b.errorLayout.isVisible = loadStates.refresh is LoadState.Error
			}
		}

		lifecycleScope.launch {
			vm.flow.collectLatest { pagingData ->
				adapter.submitData(pagingData)
			}
		}
	}
}
