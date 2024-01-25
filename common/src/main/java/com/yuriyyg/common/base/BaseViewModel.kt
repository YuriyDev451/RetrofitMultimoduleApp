package com.yuriyyg.common.base

import androidx.lifecycle.ViewModel
import com.yuriyyg.common.flowState.State
import kotlinx.coroutines.flow.MutableStateFlow

open class BaseViewModel: ViewModel() {

    val state: MutableStateFlow<State?> = MutableStateFlow(null)
}