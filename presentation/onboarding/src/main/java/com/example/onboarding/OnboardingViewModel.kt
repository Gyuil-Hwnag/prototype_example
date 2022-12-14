package com.example.onboarding

import com.example.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
) : BaseViewModel(), OnboardActionHandler {

    private val TAG = "OnboardingViewModel"

    private val _navigate: MutableSharedFlow<Unit> = MutableSharedFlow<Unit>()
    val navigate: SharedFlow<Unit> = _navigate.asSharedFlow()

    override fun onStartClicked() {
        baseViewModelScope.launch {
            _navigate.emit(Unit)
        }
    }
}