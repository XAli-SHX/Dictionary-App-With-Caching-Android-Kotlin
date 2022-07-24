package ir.alishayanpoor.dictionarywithcaching.data.remote.dto


import com.google.gson.annotations.SerializedName

class WordInfoDto : ArrayList<WordInfoDto.WordInfoDtoItem>() {
    data class WordInfoDtoItem(
        @SerializedName("word")
        val word: String,
        @SerializedName("phonetic")
        val phonetic: String,
        @SerializedName("phonetics")
        val phonetics: List<PhoneticDto>,
        @SerializedName("meanings")
        val meanings: List<MeaningDto>,
        @SerializedName("license")
        val license: LicenseDto,
        @SerializedName("sourceUrls")
        val sourceUrls: List<String>,
    ) {
        data class PhoneticDto(
            @SerializedName("text")
            val text: String,
            @SerializedName("audio")
            val audio: String,
            @SerializedName("sourceUrl")
            val sourceUrl: String,
            @SerializedName("license")
            val license: LicenseDto,
        )

        data class MeaningDto(
            @SerializedName("partOfSpeech")
            val partOfSpeech: String,
            @SerializedName("definitions")
            val definitions: List<DefinitionDto>,
            @SerializedName("synonyms")
            val synonyms: List<String>,
            @SerializedName("antonyms")
            val antonyms: List<String>,
        ) {
            data class DefinitionDto(
                @SerializedName("definition")
                val definition: String,
                @SerializedName("synonyms")
                val synonyms: List<String>,
                @SerializedName("antonyms")
                val antonyms: List<String>,
                @SerializedName("example")
                val example: String,
            )
        }

        data class LicenseDto(
            @SerializedName("name")
            val name: String,
            @SerializedName("url")
            val url: String,
        )
    }
}