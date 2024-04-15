package com.ramri.movies_challenge.ui.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramri.movies_challenge.data.model.Movie
import com.ramri.movies_challenge.data.repository.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState

    val movieList = MutableStateFlow(arrayListOf<Movie>())

    private val exceptionHandler by lazy {
        CoroutineExceptionHandler { context, throwable ->
            viewModelScope.launch {
                onError(throwable)
            }
        }
    }

    val viewModelScopeExceptionHandler by lazy { viewModelScope + exceptionHandler }

    fun load() {
        viewModelScope.launch {
            movieList.value.addAll(moviesRepository.getMovies())
        }
    }

    fun onError(throwable: Throwable) {
        _uiState.update {
            it.copy(
                isLoading = false,
                error = throwable.message
            )
        }
    }

    fun clearError() {
        _uiState.update {
            it.copy(error = null)
        }
    }

    fun showLoading() {
        _uiState.update {
            it.copy(isLoading = true, error = null)
        }
    }

    fun clearLoading() {
        _uiState.update {
            it.copy(isLoading = false)
        }
    }

    fun isLoading() = uiState.value.isLoading
}