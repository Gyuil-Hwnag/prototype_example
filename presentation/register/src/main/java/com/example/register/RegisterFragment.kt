package com.example.register

import androidx.fragment.app.viewModels
import com.example.common.BaseFragment
import com.example.register.databinding.FragmentRegisterBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding, RegisterViewModel>(R.layout.fragment_register) {

    private val TAG = "RegisterFragment"

    override val layoutResourceId: Int
        get() = R.layout.fragment_register

    override val viewModel : RegisterViewModel by viewModels()


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
