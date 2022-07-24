package ir.alishayanpoor.dictionarywithcaching.ui.view

sealed class WordInfoUiEvent {
    data class ShowSnackBar(val message: String) : WordInfoUiEvent()
}