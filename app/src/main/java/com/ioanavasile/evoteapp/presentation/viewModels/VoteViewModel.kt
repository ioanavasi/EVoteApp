package com.ioanavasile.evoteapp.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ioanavasile.evoteapp.data.api.ApiResult
import com.ioanavasile.evoteapp.domain.model.Election
import com.ioanavasile.evoteapp.domain.model.Vote
import com.ioanavasile.evoteapp.domain.repo.CandidateRepository
import com.ioanavasile.evoteapp.domain.repo.ElectionRepository
import com.ioanavasile.evoteapp.domain.repo.VoteRepository
import com.ioanavasile.evoteapp.presentation.ui.state.VoteState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VoteViewModel @Inject constructor(
    private val voteRepository: VoteRepository,
    private val candidateRepository: CandidateRepository,
    private val electionRepository: ElectionRepository
): ViewModel() {

    private val _voteState = MutableStateFlow<VoteState>(VoteState.Idle)
    val voteState: StateFlow<VoteState> = _voteState

    fun resetVoteState() {
        _voteState.value = VoteState.Idle
    }

    fun vote(election: Election, candidateId: Int) {
        viewModelScope.launch {
            _voteState.value = VoteState.Loading
            val newVote = Vote(election.id, candidateId, System.currentTimeMillis())
            when (val result = voteRepository.vote(newVote)) {
                is ApiResult.Success -> {
                    _voteState.value = VoteState.VoteSuccess(election)
                }
                is ApiResult.Error -> {
                    _voteState.value = VoteState.Error(result.message)
                }
            }
        }
    }

    fun getCandidates(election: Election) {
        viewModelScope.launch {
            _voteState.value = VoteState.Loading
            when (val result1 = candidateRepository.getCandidates(election.id)) {
                is ApiResult.Success -> {
                    when (val result2 = voteRepository.hasVoted(election.id)) {
                        is ApiResult.Success -> {
                            _voteState.value = VoteState.CandidateList(
                                election,
                                result2.data,
                                result1.data
                            )
                        }
                        is ApiResult.Error -> {
                            _voteState.value = VoteState.Error(result2.message)
                        }
                    }
                }
                is ApiResult.Error -> {
                    _voteState.value = VoteState.Error(result1.message)
                }
            }
        }
    }

    fun getElections() {
        viewModelScope.launch {
            _voteState.value = VoteState.Loading
            when (val result = electionRepository.getElections()) {
                is ApiResult.Success -> {
                    _voteState.value = VoteState.ElectionList(result.data)
                }
                is ApiResult.Error -> {
                    _voteState.value = VoteState.Error(result.message)
                }
            }
        }
    }
}