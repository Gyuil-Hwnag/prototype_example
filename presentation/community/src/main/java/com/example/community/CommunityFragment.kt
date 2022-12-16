package com.example.community

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.commnunity.R
import com.example.commnunity.databinding.FragmentCommunityBinding
import com.example.common.BaseFragment
import com.example.model.Community
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest


@AndroidEntryPoint
class CommunityFragment : BaseFragment<FragmentCommunityBinding, CommunityViewModel>(R.layout.fragment_community) {

    private val TAG = "CommunityFragment"

    override val layoutResourceId: Int
        get() = R.layout.fragment_community

    override val viewModel : CommunityViewModel by viewModels()


    override fun initStartView() {
        binding.apply {
            this.viewmodel = viewModel
            this.lifecycleOwner = viewLifecycleOwner
        }
        exception = viewModel.errorEvent
        initAdapter()
    }

    override fun initDataBinding() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.navigate.collectLatest {

            }
        }
    }

    override fun initAfterBinding() {
    }

    private fun initAdapter() {
        binding.communityRecycler.adapter = CommunityAdapter(viewModel)
    }
}
