package com.example.invite

import androidx.fragment.app.viewModels
import com.example.common.BaseFragment
import com.example.invite.databinding.FragmentInviteBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class InviteFragment : BaseFragment<FragmentInviteBinding, InviteViewModel>(R.layout.fragment_invite) {

    private val TAG = "InviteFragment"

    override val layoutResourceId: Int
        get() = R.layout.fragment_invite

    override val viewModel : InviteViewModel by viewModels()


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
