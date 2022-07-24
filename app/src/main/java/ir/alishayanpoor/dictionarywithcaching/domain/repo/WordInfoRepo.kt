package ir.alishayanpoor.dictionarywithcaching.domain.repo

import ir.alishayanpoor.dictionarywithcaching.domain.model.WordInfo
import kotlinx.coroutines.flow.Flow

interface WordInfoRepo {
    fun getWordInfo(word: String): Flow<List<WordInfo>>
}