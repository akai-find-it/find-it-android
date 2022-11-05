package pl.org.akai.hackathon.ui.lost

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import pl.org.akai.hackathon.data.model.LostItem
import pl.org.akai.hackathon.databinding.LostListItemBinding
import pl.org.akai.hackathon.ext.onClick
import pl.org.akai.hackathon.ui.base.BindingViewHolder

class LostListAdapter(
	diffCallback: DiffUtil.ItemCallback<LostItem>,
	private val onItemClick: (item: LostItem) -> Unit,
) : PagingDataAdapter<LostItem, BindingViewHolder<LostListItemBinding>>(diffCallback) {

	override fun onCreateViewHolder(
		parent: ViewGroup,
		viewType: Int,
	) = BindingViewHolder(LostListItemBinding::inflate, parent)

	override fun onBindViewHolder(
		holder: BindingViewHolder<LostListItemBinding>,
		position: Int,
	) {
		val item = getItem(position)
		holder.b.item = item ?: return
		holder.b.root.onClick {
			onItemClick(item)
		}

		holder.b.category.text = "{cmd-shape} ${item.category.name}"
	}
}
