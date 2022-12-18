package com.example.chat

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.chat.databinding.FragmentChatBinding
import com.example.common.BaseFragment
import com.example.model.Chat
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class ChatFragment : BaseFragment<FragmentChatBinding, ChatViewModel>(R.layout.fragment_chat) {

    private val TAG = "ChatFragment"

    override val layoutResourceId: Int
        get() = R.layout.fragment_chat

    override val viewModel : ChatViewModel by viewModels()
    private val chatAdapter by lazy { ChatAdapter(viewModel) }

    override fun initStartView() {
        binding.apply {
            this.viewmodel = viewModel
            this.lifecycleOwner = viewLifecycleOwner
        }
        exception = viewModel.errorEvent
        initAdapter()
        initItem()
    }

    override fun initDataBinding() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.navigate.collectLatest {
                when(it) {
                    is ChatNavigationAction.NavigateToChatDetail -> navigate(ChatFragmentDirections.actionChatFragmentToChatDetailFragment())
                }
            }
        }
    }

    override fun initAfterBinding() {
    }

    private fun initAdapter() {
        binding.chatRecycler.adapter = chatAdapter
    }

    private fun initItem() {
        val chat1 = Chat(
            chatId = 1,
            userName = "lyun",
            chatContents = "오늘은 몇시 수업인가요??"
        )
        val chat2 = Chat(
            chatId = 1,
            userName = "duru",
            chatContents = "수업이 엄청 기대 됩니다!!"
        )
        val chat3 = Chat(
            chatId = 1,
            userName = "Yun",
            chatContents = "오늘은 어떤 수업인가요??"
        )
        val chat4 = Chat(
            chatId = 4,
            userName = "Kim",
            chatContents = "수업 문의 드리려고 연락드렸습니다!!"
        )
        val chat5 = Chat(
            chatId = 5,
            userName = "공방 원장님",
            chatContents = "다음달 수업 일정 관련 연락주세요~"
        )
        val chat6 = Chat(
            chatId = 6,
            userName = "권선생님",
            chatContents = "선생님!! 오늘 7시에 수업 있습니다!"
        )
        val chat7 = Chat(
            chatId = 7,
            userName = "dida",
            chatContents = "수업 추가 신청 관련 문의 입니다."
        )
        val chat8 = Chat(
            chatId = 8,
            userName = "지나가는 사람",
            chatContents = "내일 수업은 몇시 부터 인가요??"
        )
        val chat9 = Chat(
            chatId = 9,
            userName = "신규 유저",
            chatContents = "다음달 수업 관련 문의 입니다!!"
        )
        val chat10 = Chat(
            chatId = 10,
            userName = "ㅇㅇㅇ",
            chatContents = "오늘 수업 정말 고생 많으셨습니다~"
        )

        val chatList = listOf<Chat>(chat1, chat2, chat3, chat4, chat5, chat6, chat7, chat8, chat9, chat10)
        chatAdapter.submitList(chatList)
    }
}
