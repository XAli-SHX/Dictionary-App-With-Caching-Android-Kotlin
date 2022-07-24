package ir.alishayanpoor.dictionarywithcaching.domain.use_case

import ir.alishayanpoor.dictionarywithcaching.domain.model.WordInfo
import ir.alishayanpoor.dictionarywithcaching.domain.repo.WordInfoRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class WordInfoUseCase(
    private val repo: WordInfoRepo,
) {
    fun getWordInfo(word: String): Flow<List<WordInfo>> {
        if (word.isBlank())
            return flow { }
        return repo.getWordInfo(word)
    }
}