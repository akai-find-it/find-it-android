package pl.org.akai.hackathon.ui.user

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.content.edit
import androidx.fragment.app.viewModels
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import pl.org.akai.hackathon.databinding.UserFragmentBinding
import pl.org.akai.hackathon.ui.base.BaseFragment
import pl.org.akai.hackathon.ui.base.BaseViewModel

@AndroidEntryPoint
class UserFragment : BaseFragment<UserFragmentBinding>(UserFragmentBinding::inflate) {

	override val vm: UserViewModel by viewModels()

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		b.vm = vm

		val prefs = requireContext().getSharedPreferences("user", Context.MODE_PRIVATE)
		val token = prefs.getString("token", null)

		if (token != null) {
			vm.loadUser()
		}

		vm.token.observe(viewLifecycleOwner) {
			it ?: return@observe
			requireContext().getSharedPreferences("user", Context.MODE_PRIVATE).edit {
				putString("token", it)
			}
		}
	}
}
