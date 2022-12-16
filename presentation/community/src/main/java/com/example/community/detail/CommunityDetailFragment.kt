package com.example.community.detail

import androidx.fragment.app.viewModels
import com.example.commnunity.R
import com.example.commnunity.databinding.FragmentCommunityDetailBinding
import com.example.common.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CommunityDetailFragment : BaseFragment<FragmentCommunityDetailBinding, CommunityDetailViewModel>(R.layout.fragment_community_detail) {

    private val TAG = "CommunityFragment"

    override val layoutResourceId: Int
        get() = R.layout.fragment_community_detail

    override val viewModel : CommunityDetailViewModel by viewModels()


    override fun initStartView() {
        binding.apply {
            this.viewmodel = viewModel
            this.lifecycleOwner = viewLifecycleOwner
        }
        exception = viewModel.errorEvent
    }

    override fun initDataBinding() {
    }

    override fun initAfterBinding() {
    }
}
