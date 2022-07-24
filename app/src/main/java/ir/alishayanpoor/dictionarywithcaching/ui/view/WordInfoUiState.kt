package ir.alishayanpoor.dictionarywithcaching.ui.view

import ir.alishayanpoor.dictionarywithcaching.domain.model.WordInfo

data class WordInfoUiState(
    val wordInfoItems: List<WordInfo> = emptyList(),
    val isLoading: Boolean = false,
    val searchQuery: String = "",
)