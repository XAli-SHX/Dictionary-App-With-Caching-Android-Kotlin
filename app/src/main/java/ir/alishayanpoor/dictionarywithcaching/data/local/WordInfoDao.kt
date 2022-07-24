package ir.alishayanpoor.dictionarywithcaching.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ir.alishayanpoor.dictionarywithcaching.data.local.entity.WordInfoEntity

@Dao
interface WordInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWordInfoList(infoList: List<WordInfoEntity>)

    @Query("DELETE FROM word_info_table WHERE word IN(:words)")
    suspend fun deleteWords(words: List<String>)

    @Query("SELECT * FROM word_info_table WHERE word LIKE '%' || :word || '%'")
    suspend fun getWordInfoList(word: String): List<WordInfoEntity>
}