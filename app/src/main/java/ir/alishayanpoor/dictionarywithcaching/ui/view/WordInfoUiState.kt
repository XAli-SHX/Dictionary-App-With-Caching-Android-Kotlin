package ir.alishayanpoor.dictionarywithcaching.ui.view

import ir.alishayanpoor.dictionarywithcaching.domain.model.WordInfo

data class WordInfoUiState(
    var wordInfoItems: List<WordInfo> = emptyList(),
    var isLoading: Boolean = false,
    var searchQuery: String = "",
)