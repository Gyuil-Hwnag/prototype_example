package com.example.launch

import com.example.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LaunchViewModel @Inject constructor() : BaseViewModel() {

    private val TAG = "LaunchViewModel"
}