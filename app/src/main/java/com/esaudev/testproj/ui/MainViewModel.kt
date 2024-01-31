package com.esaudev.testproj.ui

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.esaudev.testproj.domain.repository.ImagesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val imagesRepository: ImagesRepository
) : ViewModel() {

    private val _imagePayload: MutableLiveData<MainUiState> = MutableLiveData()
    val imagePayload: LiveData<MainUiState> = _imagePayload

    fun getBitmapFromUrl(
        url: String
    ) {
        viewModelScope.launch {
            _imagePayload.postValue(MainUiState.Loading)
            val result = imagesRepository.getBitmapFromUrl(url = url)
            if (result.isSuccess) {
                _imagePayload.postValue(
                    MainUiState.WithImage(
                        bitmap = result.getOrThrow()
                    )
                )
            } else {
                _imagePayload.postValue(MainUiState.Error)
            }
        }
    }

    sealed interface MainUiState {
        data class WithImage(val bitmap: Bitmap): MainUiState
        data object Loading: MainUiState
        data object Error: MainUiState
    }
}