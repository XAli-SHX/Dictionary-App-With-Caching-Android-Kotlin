package ir.alishayanpoor.dictionarywithcaching.data.remote.mapper

import ir.alishayanpoor.dictionarywithcaching.data.remote.dto.WordInfoDto
import ir.alishayanpoor.dictionarywithcaching.domain.model.Definition

fun WordInfoDto.WordInfoDtoItem.MeaningDto.DefinitionDto.toDefinition(): Definition {
    return Definition(
        definition = definition,
        synonyms = synonyms,
        antonyms = antonyms,
        example = example
    )
}