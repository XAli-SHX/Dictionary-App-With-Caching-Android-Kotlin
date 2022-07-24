package ir.alishayanpoor.dictionarywithcaching.domain.model

data class WordInfo(
    val word: String,
    val phonetic: String?,
    val meanings: List<Meaning>,
)
