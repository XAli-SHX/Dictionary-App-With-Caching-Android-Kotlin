package ir.alishayanpoor.dictionarywithcaching.data.remote

import ir.alishayanpoor.dictionarywithcaching.data.remote.dto.WordInfoDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DictionaryApi {
    @GET("{word}")
    suspend fun getWordInfo(
        @Path("word") word: String,
    ): Response<WordInfoDto>

    companion object {
        const val BASE_URL = "https://api.dictionaryapi.dev/api/v2/entries/en/"
    }
}