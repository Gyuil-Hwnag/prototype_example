package com.example.detail

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.common.BaseFragment
import com.example.detail.databinding.FragmentDetailBinding
import com.example.model.Detail
import com.example.model.Recommended
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding, DetailViewModel>(R.layout.fragment_detail) {

    private val TAG = "DetailFragment"

    override val layoutResourceId: Int
        get() = R.layout.fragment_detail

    override val viewModel : DetailViewModel by viewModels()
    private val navController by lazy { findNavController() }

    override fun initStartView() {
        binding.apply {
            this.viewmodel = viewModel
            this.lifecycleOwner = viewLifecycleOwner
        }
        exception = viewModel.errorEvent
        initToolbar()
        initItem()
    }

    override fun initDataBinding() {
    }

    override fun initAfterBinding() {
    }

    private fun initToolbar() {
        binding.backBtn.setOnClickListener {
            navController.popBackStack()
        }
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
    }
}
