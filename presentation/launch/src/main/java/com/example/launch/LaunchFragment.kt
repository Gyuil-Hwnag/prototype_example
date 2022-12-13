package com.example.launch

import androidx.fragment.app.viewModels
import com.example.common.BaseFragment
import com.example.launch.databinding.FragmentLaunchBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LaunchFragment : BaseFragment<FragmentLaunchBinding, LaunchViewModel>(R.layout.fragment_launch) {

    private val TAG = "LaunchFragment"

    override val layoutResourceId: Int
        get() = R.layout.fragment_launch

    override val viewModel : LaunchViewModel by viewModels()


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
