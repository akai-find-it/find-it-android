package pl.org.akai.hackathon.ui.item

import android.app.Activity.RESULT_OK
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pl.org.akai.hackathon.databinding.ItemFragmentBinding
import pl.org.akai.hackathon.ui.base.BaseFragment
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class ItemFragment : BaseFragment<ItemFragmentBinding>(ItemFragmentBinding::inflate) {

	override val vm: ItemViewModel by viewModels()
	private val args: ItemFragmentArgs by navArgs()

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		b.vm = vm
		vm.id = args.id

		lifecycleScope.launch(Dispatchers.IO) {
			vm.loadData()
		}

		b.imageView2.setOnClickListener {
			dispatchTakePictureIntent()
		}

		vm.data.observe(viewLifecycleOwner) {
			it ?: return@observe
			b.dateText.text =
				"{cmd-calendar} Znaleziono dnia ${it.foundAt.format(DateTimeFormatter.ISO_DATE)}"
			b.founderText.text = "{cmd-account} ${it.founder.firstName} ${it.founder.lastName}"
		}
	}

	private fun dispatchTakePictureIntent() {
		val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
		try {
			startActivityForResult(takePictureIntent, 1)
		} catch (e: ActivityNotFoundException) {
			// display error state to the user
		}

	}

	override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
		super.onActivityResult(requestCode, resultCode, data)
		if (requestCode == 1 && resultCode == RESULT_OK) {
			val imageBitmap = data?.extras?.get("data") as Bitmap
			b.imageView2.setImageBitmap(imageBitmap)
		}
	}
}
