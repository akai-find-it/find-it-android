package pl.org.akai.hackathon.ext

import android.view.LayoutInflater
import android.view.View
import androidx.activity.ComponentActivity
import androidx.viewbinding.ViewBinding

public inline fun <reified B : ViewBinding> ComponentActivity.viewBinding(
	crossinline inflater: (LayoutInflater) -> B,
) = lazy(LazyThreadSafetyMode.NONE) {
	inflater(layoutInflater)
}

fun <V : View> V.onClick(block: (v: V) -> Unit) {
	setOnClickListener {
		block(this)
	}
}
