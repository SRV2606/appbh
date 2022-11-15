package com.example.appbharat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {

    private val _memeRespData: MutableStateFlow<ServerMemes?> = MutableStateFlow(null)
    val _memeData = _memeRespData.asStateFlow()


    fun getMemes() {
        viewModelScope.launch {
            val response = mainRepository.getMemes()
            if (response?.success == true && response.data != null) {
                _memeRespData.emit(response)
            }
        }
    }

}