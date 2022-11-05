package pl.org.akai.hackathon.ui.add

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import pl.org.akai.hackathon.R
import pl.org.akai.hackathon.data.model.AddModel
import pl.org.akai.hackathon.data.model.Question
import pl.org.akai.hackathon.databinding.AddAnswerItemBinding

class AddAnswerAdapter :
	ListAdapter<Question, AddAnswerAdapter.ViewHolder>(QuestionDiffCallback()) {

	class ViewHolder(val binding: AddAnswerItemBinding) :
		RecyclerView.ViewHolder(binding.root) {
		companion object {
			@LayoutRes
			val LAYOUT = R.layout.add_answer_item
		}
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		val withDataBinding: AddAnswerItemBinding = DataBindingUtil.inflate(
			LayoutInflater.from(parent.context),
			ViewHolder.LAYOUT,
			parent,
			false
		)
		return ViewHolder(withDataBinding)
	}

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		holder.binding.also {
			it.question = getItem(position)
			it.answer = AddModel.Answer(getItem(position).id, "")
		}
	}
}

class QuestionDiffCallback : DiffUtil.ItemCallback<Question>() {
	override fun areItemsTheSame(oldItem: Question, newItem: Question): Boolean {
		return oldItem.id == newItem.id
	}

	override fun areContentsTheSame(oldItem: Question, newItem: Question): Boolean {
		return oldItem == newItem
	}

}