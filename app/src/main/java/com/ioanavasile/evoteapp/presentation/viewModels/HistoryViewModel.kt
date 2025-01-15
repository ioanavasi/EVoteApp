package com.ioanavasile.evoteapp.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ioanavasile.evoteapp.data.api.ApiResult
import com.ioanavasile.evoteapp.domain.repo.VoteRepository
import com.ioanavasile.evoteapp.presentation.ui.state.HistoryState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val voteRepository: VoteRepository,
) : ViewModel() {

    private val _historyState = MutableStateFlow<HistoryState>(HistoryState.Idle)
    val historyState: StateFlow<HistoryState> = _historyState

    fun resetHistoryState() {
        _historyState.value = HistoryState.Idle
    }

    fun getVoteHistory() {
        viewModelScope.launch {
            _historyState.value = HistoryState.Loading
            when (val result = voteRepository.getVoteHistory()) {
                is ApiResult.Success -> {
                    _historyState.value = HistoryState.Success(result.data)
                }

                is ApiResult.Error -> {
                    _historyState.value = HistoryState.Error(result.message)
                }
            }
        }
    }
}