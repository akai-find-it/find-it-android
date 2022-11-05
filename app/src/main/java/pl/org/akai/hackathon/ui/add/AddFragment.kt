package pl.org.akai.hackathon.ui.add

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.google.android.material.datepicker.MaterialDatePicker
import dagger.hilt.android.AndroidEntryPoint
import pl.org.akai.hackathon.databinding.AddFragmentBinding
import pl.org.akai.hackathon.ext.toISODate
import pl.org.akai.hackathon.ui.base.BaseFragment

@AndroidEntryPoint
class AddFragment : BaseFragment<AddFragmentBinding>(AddFragmentBinding::inflate) {

	override val vm: AddViewModel by viewModels()

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		b.vm = vm
		val datePicker =
			MaterialDatePicker.Builder.datePicker()
				.setTitleText("Select date")
				.setSelection(MaterialDatePicker.todayInUtcMilliseconds())
				.build()
		datePicker.addOnPositiveButtonClickListener {
			b.date.setText(datePicker.selection!!.toISODate()) //always date selected because setSelection()
		}

		b.date.setOnClickListener {
			datePicker.show(parentFragmentManager, "DatePicker")
		}
	}
}