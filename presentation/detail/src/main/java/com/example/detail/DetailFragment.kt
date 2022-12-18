package com.example.detail

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.common.BaseFragment
import com.example.detail.databinding.FragmentDetailBinding
import com.example.model.Comments
import com.example.model.Detail
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding, DetailViewModel>(R.layout.fragment_detail) {

    private val TAG = "DetailFragment"

    override val layoutResourceId: Int
        get() = R.layout.fragment_detail

    override val viewModel : DetailViewModel by viewModels()
    private val navController by lazy { findNavController() }
    private val detailCommentsAdapter = DetailCommentsAdapter()

    override fun initStartView() {
        binding.apply {
            this.viewmodel = viewModel
            this.lifecycleOwner = viewLifecycleOwner
        }
        exception = viewModel.errorEvent
        initToolbar()
        initItem()
        initAdapter()
    }

    override fun initDataBinding() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.navigate.collectLatest {
                when(it) {
                    is DetailNavigationAction.NavigateToOverCount -> toastMessage("이미 참가 하였습니다!!")
                    is DetailNavigationAction.NavigateToInvite -> toastMessage("참여가 완료되었습니다!!")
                }
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.commentList.collectLatest {
                detailCommentsAdapter.submitList(it)
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
        binding.commentsRecycler.adapter = detailCommentsAdapter
    }

    private fun initItem() {
        val detail = Detail(
            detailId = 1,
            detailImg = R.drawable.children_paint,
            detailName = "아이들과 함께하는 제작",
            userName = "[TEAM] 메이커스",
            contents = "아이들과 디자인 부터 제작까지 함께하는 케릭터 인형 만들기",
            count = 43
        )
        viewModel.setDetail(detail)

        val comment1 = Comments(
            userName = "타이차우",
            contents = "좋은향도많고 사장님도 친절하셔서 기분좋게 다녀왔어요!!"
        )
        val comment2 = Comments(
            userName = "선영",
            contents = "친절하세요 1:1 클래스 수업도 가능해요\uD83D\uDE00\uD83D\uDE00\n" +
                    "천연비누\uD83D\uDC4D\uD83D\uDC4D\uD83D\uDC4D"
        )
        val comment3 = Comments(
            userName = "lyun",
            contents = "사장님 너무 친절하세요! 착한 소비 할 수 있어 행복합니다ㅎㅎ"
        )
        val comment4 = Comments(
            userName = "duru",
            contents = "좋은향도많고 사장님도 친절하셔서 기분좋게 다녀왔어요!!"
        )
        val comment5 = Comments(
            userName = "sonn",
            contents = "선생님이 너무 친절하세요\uD83D\uDE4F\n" +
                    "오늘 첫 뜨게하는 건데 너무 잘 배웠어요\uD83E\uDD79❤️"
        )
        val comment6 = Comments(
            userName = "꼬마고객",
            contents = "오늘 친구들이랑 같이 만들었어요\uD83D\uDE0D\n" +
                    "너어어너어무 친절하게 알려주셔소 재밌게 만들고왔어용 :)\n" +
                    "가방도 너무 맘에 들어요\uD83E\uDDE1\uD83E\uDDE1 감사합니당 !!"
        )
        val comment7 = Comments(
            userName = "슈비",
            contents = "처음 시도해보는 자이언트 얀 가방이었는데 정말 재미있고\n" +
                    "유익한 시간이었습니다:) 정말 사장님도 친절하시고 이번 겨\n" +
                    "울에 잘 들고 다닐 수 있겠어요~ 완전 추천 합니다!"
        )
        val commentList = listOf<Comments>(comment1, comment2, comment3, comment4, comment5, comment6, comment7)
        viewModel.setCommentList(commentList)
    }
}
