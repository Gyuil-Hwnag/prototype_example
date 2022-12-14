package com.example.detail

import androidx.fragment.app.viewModels
import com.example.common.BaseFragment
import com.example.detail.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding, DetailViewModel>(R.layout.fragment_detail) {

    private val TAG = "DetailFragment"

    override val layoutResourceId: Int
        get() = R.layout.fragment_detail

    override val viewModel : DetailViewModel by viewModels()


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
