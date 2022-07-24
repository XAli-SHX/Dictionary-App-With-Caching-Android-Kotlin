package ir.alishayanpoor.dictionarywithcaching.ui.view

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ir.alishayanpoor.dictionarywithcaching.domain.use_case.WordInfoUseCase
import ir.alishayanpoor.dictionarywithcaching.exception.AppException
import ir.alishayanpoor.dictionarywithcaching.util.CHAR_ENTRY_SEARCH_DELAY
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class WordInfoViewModel @Inject constructor(
    private val wordInfoUseCase: WordInfoUseCase,
) : ViewModel() {
    val state by mutableStateOf(WordInfoUiState())
    val event = Channel<WordInfoUiEvent>()

    private var searchJob: Job? = null

    fun onSearch(query: String) {
        state.searchQuery = query
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(CHAR_ENTRY_SEARCH_DELAY)
            try {
                state.isLoading = true
                wordInfoUseCase.getWordInfo(query).collectLatest {
                    state.wordInfoItems = it
                }
            } catch (e: AppException) {
                event.send(WordInfoUiEvent.ShowSnackBar(e.localizedMessage.orEmpty()))
            }
            state.isLoading = false
        }
    }
}