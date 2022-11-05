package pl.org.akai.hackathon.ui.chat

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.github.bassaer.chatmessageview.model.ChatUser
import com.github.bassaer.chatmessageview.model.Message
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pl.org.akai.hackathon.R
import pl.org.akai.hackathon.data.model.ChatMessage
import pl.org.akai.hackathon.databinding.ChatFragmentBinding
import pl.org.akai.hackathon.ui.base.BaseFragment
import java.util.*

class ChatFragment : BaseFragment<ChatFragmentBinding>(ChatFragmentBinding::inflate) {

	override val vm: ChatViewModel by viewModels()
	private val args: ChatFragmentArgs by navArgs()

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		val me = ChatUser(0, "Ja", BitmapFactory.decodeResource(resources, R.drawable.profile))
		val you = ChatUser(args.userId, args.userName, BitmapFactory.decodeResource(resources, R.drawable.profile))

		with(b.chatView) {
			setOnClickSendButtonListener {
				val message = ChatMessage(
					userId = args.userId,
					userName = args.userName,
					isSent = true,
					text = b.chatView.inputText,
					date = System.currentTimeMillis(),
				)
				lifecycleScope.launch(Dispatchers.IO) {
					vm.sendMessage(args.userId, message)
				}
				b.chatView.send(message.toMessage(me, you))
			}
		}

		vm.appDb.chatDao().getByPersonId(args.userId).observe(viewLifecycleOwner) {
			for (message in it) {
				if (message.isSent) {
					b.chatView.send(message.toMessage(me, you))
				} else {
					b.chatView.receive(message.toMessage(me, you))
				}
			}
		}
	}

	private fun ChatMessage.toMessage(me: ChatUser, you: ChatUser) = Message.Builder()
		.setUser(if (isSent) me else you)
		.setRight(isSent)
		.setText(text)
		.setSendTime(Calendar.getInstance().also {
			it.timeInMillis = this.date
		})
		.hideIcon(isSent)
		.build()
}
