package ir.alishayanpoor.dictionarywithcaching.data.remote.mapper

import ir.alishayanpoor.dictionarywithcaching.data.remote.dto.WordInfoDto
import ir.alishayanpoor.dictionarywithcaching.domain.model.Meaning

fun WordInfoDto.WordInfoDtoItem.MeaningDto.toMeaning(): Meaning {
    return Meaning(
        definition = definitions.map { it.toDefinition() },
        partOfSpeech = partOfSpeech
    )
}