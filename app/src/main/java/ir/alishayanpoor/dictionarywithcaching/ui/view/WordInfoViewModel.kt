package ir.alishayanpoor.dictionarywithcaching.ui.view

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.alishayanpoor.dictionarywithcaching.domain.use_case.WordInfoUseCase
import ir.alishayanpoor.dictionarywithcaching.exception.AppException
import ir.alishayanpoor.dictionarywithcaching.util.CHAR_ENTRY_SEARCH_DELAY
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WordInfoViewModel @Inject constructor(
    private val wordInfoUseCase: WordInfoUseCase,
) : ViewModel() {
    var state by mutableStateOf(WordInfoUiState())
        private set
    val event = Channel<WordInfoUiEvent>()

    private var searchJob: Job? = null

    fun onSearch(query: String) {
//        state.searchQuery = query
        state = state.copy(searchQuery = query)
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(CHAR_ENTRY_SEARCH_DELAY)
            try {
                state = state.copy(
                    isLoading = true
                )
                wordInfoUseCase.getWordInfo(query).collectLatest {
                    state = state.copy(
                        wordInfoItems = it
                    )
                }
            } catch (e: AppException) {
                event.send(WordInfoUiEvent.ShowSnackBar(e.localizedMessage.orEmpty()))
            }
            state = state.copy(
                isLoading = false
            )
        }
    }
}