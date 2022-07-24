package ir.alishayanpoor.dictionarywithcaching.data.remote.mapper

import ir.alishayanpoor.dictionarywithcaching.data.local.entity.WordInfoEntity
import ir.alishayanpoor.dictionarywithcaching.data.remote.dto.WordInfoDto
import ir.alishayanpoor.dictionarywithcaching.domain.model.WordInfo

fun WordInfoDto.WordInfoDtoItem.toWordInfoEntity(): WordInfoEntity {
    return WordInfoEntity(
        word = word,
        phonetic = phonetic,
        meanings = meanings.map { it.toMeaning() },
    )
}

fun WordInfoDto.WordInfoDtoItem.toWordInfo(): WordInfo {
    return WordInfo(
        word = word,
        phonetic = phonetic,
        meanings = meanings.map { it.toMeaning() },
    )
}