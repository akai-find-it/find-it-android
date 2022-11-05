package pl.org.akai.hackathon.ui.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import pl.org.akai.hackathon.R
import pl.org.akai.hackathon.data.model.ChatMessage
import pl.org.akai.hackathon.databinding.ChatListItemBinding
import pl.org.akai.hackathon.ext.onClick

class ChatListAdapter(
	private val onClick: (message: ChatMessage) -> Unit,
) : ListAdapter<ChatMessage, ChatListAdapter.ViewHolder>(QuestionDiffCallback()) {

	class ViewHolder(val binding: ChatListItemBinding) :
		RecyclerView.ViewHolder(binding.root) {
		companion object {
			@LayoutRes
			val LAYOUT = R.layout.chat_list_item
		}
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		val withDataBinding: ChatListItemBinding = DataBindingUtil.inflate(
			LayoutInflater.from(parent.context),
			ViewHolder.LAYOUT,
			parent,
			false
		)
		return ViewHolder(withDataBinding)
	}

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		holder.binding.also {
			it.chatMessage = getItem(position)
			it.root.onClick {
				onClick(getItem(position))
			}
		}
	}
}

class QuestionDiffCallback : DiffUtil.ItemCallback<ChatMessage>() {
	override fun areItemsTheSame(
		oldItem: ChatMessage,
		newItem: ChatMessage,
	): Boolean {
		return oldItem.id == newItem.id
	}

	override fun areContentsTheSame(
		oldItem: ChatMessage,
		newItem: ChatMessage,
	): Boolean {
		return oldItem == newItem
	}

}
