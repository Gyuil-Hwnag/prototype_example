package com.example.launch

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.common.BaseFragment
import com.example.launch.databinding.FragmentLaunchBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest


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
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.navigate.collectLatest {
                when(it) {
                    is LaunchNavigationAction.NavigateToLogin -> navigate(LaunchFragmentDirections.actionLaunchFragmentToHomeFragment())
                    is LaunchNavigationAction.NavigateToRegister -> navigate(LaunchFragmentDirections.actionLaunchFragmentToRegisterFragment())
                }
            }
        }
    }

    override fun initAfterBinding() {
    }
}
