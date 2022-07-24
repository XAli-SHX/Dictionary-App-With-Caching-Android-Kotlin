package ir.alishayanpoor.dictionarywithcaching.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ir.alishayanpoor.dictionarywithcaching.domain.model.Meaning

@Entity(tableName = "word_info_table")
data class WordInfoEntity(
    val word: String,
    val phonetic: String,
    val meanings: List<Meaning>,
    @PrimaryKey val id: Int? = null,
)