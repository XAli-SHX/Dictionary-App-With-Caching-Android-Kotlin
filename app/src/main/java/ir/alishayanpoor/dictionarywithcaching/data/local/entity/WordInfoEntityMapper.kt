package ir.alishayanpoor.dictionarywithcaching.data.local.entity

import ir.alishayanpoor.dictionarywithcaching.domain.model.WordInfo

fun WordInfoEntity.toWordInfo(): WordInfo {
    return WordInfo(
        meanings = meanings,
        word = word,
        phonetic = phonetic
    )
}