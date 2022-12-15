package com.example.register.info

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.common.BaseFragment
import com.example.register.R
import com.example.register.databinding.FragmentRegisterInfoBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.prefs.Preferences


@AndroidEntryPoint
class RegisterInfoFragment : BaseFragment<FragmentRegisterInfoBinding, RegisterInfoViewModel>(R.layout.fragment_register_info) {

    private val TAG = "RegisterInfoFragment"

    override val layoutResourceId: Int
        get() = R.layout.fragment_register_info

    override val viewModel : RegisterInfoViewModel by viewModels()
    private val navController by lazy { findNavController() }


    override fun initStartView() {
        binding.apply {
            this.viewmodel = viewModel
            this.lifecycleOwner = viewLifecycleOwner
        }
        exception = viewModel.errorEvent
    }

    override fun initDataBinding() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {

            launch {
                viewModel.navigate.collectLatest {
                    when(it) {
                        is RegisterInfoNavigationAction.NavigateToPasswordNotMatched -> toastMessage("비밀번호가 일치하지 않습니다!")
                        is RegisterInfoNavigationAction.NavigateToEmpty -> toastMessage("빈칸이 존재합니다!")
                        is RegisterInfoNavigationAction.NavigateToSuccess -> {
                            editor.putString(email, it.email)
                            editor.putString(password, it.password)
                            editor.commit()
                            navigate(RegisterInfoFragmentDirections.actionRegisterInfoFragmentToLaunchFragment())
                        }
                    }
                }
            }
        }
    }

    override fun initAfterBinding() {
        binding.backBtn.setOnClickListener {
            navController.popBackStack()
        }
    }
}
