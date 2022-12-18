package com.example.chat.detail

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.chat.R
import com.example.chat.databinding.FragmentChatBinding
import com.example.chat.databinding.FragmentChatDetailBinding
import com.example.common.BaseFragment
import com.example.model.ChatDetail
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class ChatDetailFragment : BaseFragment<FragmentChatDetailBinding, ChatDetailViewModel>(R.layout.fragment_chat_detail) {

    private val TAG = "ChatDetailFragment"

    override val layoutResourceId: Int
        get() = R.layout.fragment_chat_detail

    override val viewModel : ChatDetailViewModel by viewModels()
    private val navController by lazy { findNavController() }
    private val chatDetailAdapter = ChatDetailAdapter()

    override fun initStartView() {
        binding.apply {
            this.viewmodel = viewModel
            this.lifecycleOwner = viewLifecycleOwner
        }
        exception = viewModel.errorEvent
        initAdapter()
        initToolbar()
        initItem()
    }

    override fun initDataBinding() {
        lifecycleScope.launchWhenStarted {
            viewModel.commentList.collectLatest {
                chatDetailAdapter.submitList(it)
                chatDetailAdapter.notifyDataSetChanged()
            }
        }
    }

    override fun initAfterBinding() {
    }

    private fun initToolbar() {
        binding.backBtn.setOnClickListener {
            navController.popBackStack()
        }
    }

    private fun initAdapter() {
        binding.chatRecycler.adapter = chatDetailAdapter
    }

    private fun initItem() {
        val chat1 = ChatDetail(
            chatId = 1,
            chatType = 0,
            userName = "lyun",
            chatContents = "수업 문의 관련해서 연락드렸습니다!!"
        )
        val chat2 = ChatDetail(
            chatId = 2,
            chatType = 1,
            userName = "lyun",
            chatContents = "수업은 매주 1회 저녁 7시 입니다!!"
        )
        val chat3 = ChatDetail(
            chatId = 3,
            chatType = 0,
            userName = "lyun",
            chatContents = "아하 알겠습니다~"
        )
        val chat4 = ChatDetail(
            chatId = 4,
            chatType = 0,
            userName = "lyun",
            chatContents = "수업 신청했습니다!! \n 다음수업 때 뵐게요~"
        )
        val chat5 = ChatDetail(
            chatId = 5,
            chatType = 1,
            userName = "lyun",
            chatContents = "넵 알겠습니다!!"
        )
        val chat6 = ChatDetail(
            chatId = 6,
            chatType = 0,
            userName = "lyun",
            chatContents = "혹시 수업 때 준비해야 할 것들 있을까요??"
        )
        val chat7 = ChatDetail(
            chatId = 1,
            chatType = 1,
            userName = "lyun",
            chatContents = "수업때는 따로 준비물은 없습니다!!\n 다음번 수업때 뵐게요~"
        )
        val chat8 = ChatDetail(
            chatId = 8,
            chatType = 0,
            userName = "lyun",
            chatContents = "넵!! 다음번 수업 때 뵐게요ㅎㅎ"
        )
        val chat9 = ChatDetail(
            chatId = 1,
            chatType = 0,
            userName = "lyun",
            chatContents = "오늘 수업 정말 고생 많으셨습니다~"
        )
        val chat10 = ChatDetail(
            chatId = 1,
            chatType = 1,
            userName = "lyun",
            chatContents = "오늘 수업 정말 고생 많으셨습니다!!"
        )
        val chatList = listOf(chat1, chat2, chat3, chat4, chat5, chat6, chat7, chat8, chat9, chat10)
        viewModel.setCommentList(chatList)
    }
}
