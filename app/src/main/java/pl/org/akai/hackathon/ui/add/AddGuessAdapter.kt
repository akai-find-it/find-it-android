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
import pl.org.akai.hackathon.databinding.AddGuessItemBinding

class AddGuessAdapter :
	ListAdapter<Pair<Question, AddModel.Guess>, AddGuessAdapter.ViewHolder>(QuestionDiffCallback2()) {

	class ViewHolder(val binding: AddGuessItemBinding) :
		RecyclerView.ViewHolder(binding.root) {
		companion object {
			@LayoutRes
			val LAYOUT = R.layout.add_guess_item
		}
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		val withDataBinding: AddGuessItemBinding = DataBindingUtil.inflate(
			LayoutInflater.from(parent.context),
			ViewHolder.LAYOUT,
			parent,
			false
		)
		return ViewHolder(withDataBinding)
	}

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		holder.binding.also {
			it.question = getItem(position).first
			it.guess = getItem(position).second
		}
	}
}

class QuestionDiffCallback2 : DiffUtil.ItemCallback<Pair<Question, AddModel.Guess>>() {
	override fun areItemsTheSame(
		oldItem: Pair<Question, AddModel.Guess>,
		newItem: Pair<Question, AddModel.Guess>,
	): Boolean {
		return oldItem.first.id == newItem.first.id
	}

	override fun areContentsTheSame(
		oldItem: Pair<Question, AddModel.Guess>,
		newItem: Pair<Question, AddModel.Guess>,
	): Boolean {
		return oldItem == newItem
	}

}
