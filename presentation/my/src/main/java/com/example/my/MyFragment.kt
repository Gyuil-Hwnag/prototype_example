package com.example.my

import android.content.Intent
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.common.BaseFragment
import com.example.my.databinding.FragmentMyBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

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
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.navigate.collectLatest {
                when(it) {
                    is MyNavigationAction.NavigateToLogout -> navigate(MyFragmentDirections.actionLaunchFragment())
                    is MyNavigationAction.NavigateToDelete -> {
                        editor.remove(email)
                        editor.remove(password)
                        editor.commit()
                        navigate(MyFragmentDirections.actionLaunchFragment())
                    }
                    is MyNavigationAction.NavigateToSend -> sendEmail()
                }
            }

        }
    }

    override fun initAfterBinding() {
    }

    private fun sendEmail() {
        val address = arrayOf("195915@naver.com")
        val email = Intent(Intent.ACTION_SEND)
        email.apply {
            type = "*/*"
            putExtra(Intent.EXTRA_EMAIL, address)
            putExtra(Intent.EXTRA_SUBJECT, "창조공방 문의하기")
            putExtra(Intent.EXTRA_TEXT, "이름: \n 연락처: \n, 문의내용")
        }

        startActivity(email)
    }
}
