package com.example.makhazany.Presentation.Ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.makhazany.Data.Local.Entity.InboundEntity
import com.example.makhazany.Domain.Repository.InboundRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InboundViewModel @Inject constructor(
    private val repository: InboundRepository
) : ViewModel() {

    private val _selectedItemId = MutableStateFlow<Int?>(null)
    
    val inboundHistory: StateFlow<List<InboundEntity>> = _selectedItemId
        .filterNotNull()
        .flatMapLatest { itemId ->
            repository.getInboundByItem(itemId)
        }
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun selectItem(itemId: Int) {
        _selectedItemId.value = itemId
    }

    fun addInbound(inbound: InboundEntity) {
        viewModelScope.launch {
            repository.insertInbound(inbound)
        }
    }
}
