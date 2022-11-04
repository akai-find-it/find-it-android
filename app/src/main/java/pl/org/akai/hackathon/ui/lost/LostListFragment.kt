package pl.org.akai.hackathon.ui.lost

import androidx.fragment.app.viewModels
import pl.org.akai.hackathon.databinding.LostListFragmentBinding
import pl.org.akai.hackathon.ui.base.BaseFragment

class LostListFragment : BaseFragment<LostListFragmentBinding>({ inflater, parent ->
	LostListFragmentBinding.inflate(inflater, parent, false)
}) {

	override val vm: LostListViewModel by viewModels()
}
