package com.example.makhazany.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.makhazany.data.local.entity.OutboundDetailsEntity
import com.example.makhazany.data.local.entity.OutboundEntity
import com.example.makhazany.domain.repository.OutboundRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OutboundViewModel @Inject constructor(
    private val repository: OutboundRepository
) : ViewModel() {

    val outboundList = repository
        .getOutboundWithDetails()
        .stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            emptyList()
        )

    fun insertOutboundWithDetails(
        outbound: OutboundEntity,
        details: List<OutboundDetailsEntity>
    ) {
        viewModelScope.launch {
            val outboundId = repository.insertOutbound(outbound)
            val detailsWithId = details.map {
                it.copy(outboundId = outboundId.toInt())
            }
            repository.insertDetails(detailsWithId)
        }
    }
}