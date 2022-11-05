package pl.org.akai.hackathon.ui.add

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.datepicker.MaterialDatePicker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pl.org.akai.hackathon.data.model.AddModel
import pl.org.akai.hackathon.databinding.AddFragmentBinding
import pl.org.akai.hackathon.ext.toISODate
import pl.org.akai.hackathon.ext.toLocalDate
import pl.org.akai.hackathon.ui.base.BaseFragment
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class AddFragment : BaseFragment<AddFragmentBinding>(AddFragmentBinding::inflate) {

	override val vm: AddViewModel by viewModels()

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		b.vm = vm
		val model = AddModel()
		b.model = model
		val datePicker =
			MaterialDatePicker.Builder.datePicker()
				.setTitleText("Select date")
				.setSelection(MaterialDatePicker.todayInUtcMilliseconds())
				.build()
		datePicker.addOnPositiveButtonClickListener {
			b.date.setText(datePicker.selection!!.toISODate()) //always date selected because setSelection()
			model.foundAt = datePicker.selection!!.toLocalDate()
		}
		lifecycleScope.launch(Dispatchers.IO) {
			vm.loadData()
		}

		b.date.setOnClickListener {
			datePicker.show(parentFragmentManager, "DatePicker")
		}
		b.addButton.setOnClickListener {
			findNavController().navigate(
				AddFragmentDirections.actionAddFragmentToAddAnswerFragment(
					model.name,
					b.category.editText?.text.toString(),
					model.description,
					model.foundAt?.format(
						DateTimeFormatter.ISO_DATE
					) ?: ""  //TODO
				)
			)
		}
	}
}