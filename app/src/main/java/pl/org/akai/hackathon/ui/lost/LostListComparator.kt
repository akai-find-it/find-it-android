package pl.org.akai.hackathon.ui.lost

import androidx.recyclerview.widget.DiffUtil
import pl.org.akai.hackathon.data.model.LostItem

object LostListComparator : DiffUtil.ItemCallback<LostItem>() {

	override fun areItemsTheSame(oldItem: LostItem, newItem: LostItem): Boolean {
		return oldItem.id == newItem.id
	}

	override fun areContentsTheSame(oldItem: LostItem, newItem: LostItem): Boolean {
		return oldItem == newItem
	}
}
