package com.example.onboarding

import androidx.fragment.app.viewModels
import com.example.common.BaseFragment
import com.example.onboarding.databinding.FragmentOnboardingBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class OnboardingFragment : BaseFragment<FragmentOnboardingBinding, OnboardingViewModel>(R.layout.fragment_onboarding) {

    private val TAG = "OnboardingFragment"

    override val layoutResourceId: Int
        get() = R.layout.fragment_onboarding

    override val viewModel : OnboardingViewModel by viewModels()


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
