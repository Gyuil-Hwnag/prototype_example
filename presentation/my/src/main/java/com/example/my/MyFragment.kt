package com.example.my

import androidx.fragment.app.viewModels
import com.example.common.BaseFragment
import com.example.my.databinding.FragmentMyBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyFragment : BaseFragment<FragmentMyBinding, MyViewModel>(R.layout.fragment_my) {

    private val TAG = "MyFragment"

    override val layoutResourceId: Int
        get() = R.layout.fragment_my

    override val viewModel : MyViewModel by viewModels()


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
