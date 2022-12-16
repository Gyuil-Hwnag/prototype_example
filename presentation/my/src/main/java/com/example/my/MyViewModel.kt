package com.example.my

import com.example.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor() : BaseViewModel() {

    private val TAG = "MyViewModel"
}