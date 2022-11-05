package pl.org.akai.hackathon.ui.chat

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pl.org.akai.hackathon.databinding.ChatListFragmentBinding
import pl.org.akai.hackathon.ui.base.BaseFragment

@AndroidEntryPoint
class ChatListFragment : BaseFragment<ChatListFragmentBinding>(ChatListFragmentBinding::inflate) {

	override val vm: ChatListViewModel by viewModels()

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		val adapter = ChatListAdapter() {
			findNavController().navigate(ChatListFragmentDirections.actionChatListFragmentToChatFragment(
				userId = it.userId,
				userName = it.userName,
			))
		}
		b.list.adapter = adapter
		b.list.layoutManager = LinearLayoutManager(context)

		vm.data.observe(viewLifecycleOwner) {
			adapter.submitList(it)
		}

		lifecycleScope.launch(Dispatchers.IO) {
			vm.loadData()
		}
	}
}
