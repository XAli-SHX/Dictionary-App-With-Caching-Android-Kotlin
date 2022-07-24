package ir.alishayanpoor.dictionarywithcaching.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken
import ir.alishayanpoor.dictionarywithcaching.domain.model.Meaning
import ir.alishayanpoor.dictionarywithcaching.util.JsonParser

@ProvidedTypeConverter
class Converters(
    private val jsonParser: JsonParser,
) {
    @TypeConverter
    fun fromMeaningsJson(json: String): List<Meaning> {
        return jsonParser.fromJson(
            json,
            object : TypeToken<ArrayList<Meaning>>() {}.type
        ) ?: emptyList()
    }

    @TypeConverter
    fun toMeaningJson(meanings: List<Meaning>): String {
        return jsonParser.toJson(
            meanings,
            object : TypeToken<ArrayList<Meaning>>() {}.type
        ) ?: "[]"
    }
}