package com.example.onboarding

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.common.BaseFragment
import com.example.common.util.LinePagerIndicatorDecoration
import com.example.common.util.addSnapPagerScroll
import com.example.onboarding.databinding.FragmentOnboardingBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest


@AndroidEntryPoint
class OnboardingFragment : BaseFragment<FragmentOnboardingBinding, OnboardingViewModel>(R.layout.fragment_onboarding) {

    private val TAG = "OnboardingFragment"

    override val layoutResourceId: Int
        get() = R.layout.fragment_onboarding

    override val viewModel : OnboardingViewModel by viewModels()
    private val onboardAdapter = OnboardAdapter()

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
                navigate(OnboardingFragmentDirections.actionOnboardingFramgentToLaunchFragment())
            }
        }
    }

    override fun initAfterBinding() {
    }

    private fun initAdapter() {
        val itemList = listOf<Int>(
            R.mipmap.img_onboard_1_foreground,
            R.mipmap.img_onboard_2_foreground,
            R.mipmap.img_onboard_3_foreground,
            R.mipmap.img_onboard_4_foreground
        )
        onboardAdapter.submitList(itemList)
        binding.onboardRecycler.apply {
            this.adapter = onboardAdapter
            this.addSnapPagerScroll()
            this.addItemDecoration(LinePagerIndicatorDecoration())
        }
    }
}
