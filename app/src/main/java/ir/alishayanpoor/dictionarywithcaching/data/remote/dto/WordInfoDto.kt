package ir.alishayanpoor.dictionarywithcaching.data.remote.dto


import com.google.gson.annotations.SerializedName

class WordInfoDto : ArrayList<WordInfoDto.WordInfoDtoItem>() {
    data class WordInfoDtoItem(
        @SerializedName("word")
        val word: String,
        @SerializedName("phonetic")
        val phonetic: String,
        @SerializedName("phonetics")
        val phonetics: List<Phonetic>,
        @SerializedName("meanings")
        val meanings: List<Meaning>,
        @SerializedName("license")
        val license: License,
        @SerializedName("sourceUrls")
        val sourceUrls: List<String>,
    ) {
        data class Phonetic(
            @SerializedName("text")
            val text: String,
            @SerializedName("audio")
            val audio: String,
            @SerializedName("sourceUrl")
            val sourceUrl: String,
            @SerializedName("license")
            val license: License,
        ) {
            data class License(
                @SerializedName("name")
                val name: String,
                @SerializedName("url")
                val url: String,
            )
        }

        data class Meaning(
            @SerializedName("partOfSpeech")
            val partOfSpeech: String,
            @SerializedName("definitions")
            val definitions: List<Definition>,
            @SerializedName("synonyms")
            val synonyms: List<String>,
            @SerializedName("antonyms")
            val antonyms: List<String>,
        ) {
            data class Definition(
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

        data class License(
            @SerializedName("name")
            val name: String,
            @SerializedName("url")
            val url: String,
        )
    }
}