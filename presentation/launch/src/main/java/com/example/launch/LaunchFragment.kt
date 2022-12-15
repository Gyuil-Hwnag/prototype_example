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
                    is LaunchNavigationAction.NavigateToLogin -> {
                        val email = sSharedPreferences.getString(email, "")
                        val password = sSharedPreferences.getString(password, "")

                        if((email == it.id) && (password == it.password))
                            navigate(LaunchFragmentDirections.actionLaunchFragmentToHomeFragment())
                        else
                            toastMessage("이메일 및 비밀번호가 다릅니다!")
                    }
                    is LaunchNavigationAction.NavigateToRegister -> navigate(LaunchFragmentDirections.actionLaunchFragmentToRegisterFragment())
                    is LaunchNavigationAction.NavigateToEmpty -> toastMessage("이메일 또는 비밀번호가 비어 있습니다!")
                }
            }
        }
    }

    override fun initAfterBinding() {
    }
}
