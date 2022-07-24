package ir.alishayanpoor.dictionarywithcaching.data.repo

import ir.alishayanpoor.dictionarywithcaching.data.local.WordInfoDao
import ir.alishayanpoor.dictionarywithcaching.data.local.entity.toWordInfo
import ir.alishayanpoor.dictionarywithcaching.data.remote.DictionaryApi
import ir.alishayanpoor.dictionarywithcaching.data.remote.mapper.toWordInfo
import ir.alishayanpoor.dictionarywithcaching.data.remote.mapper.toWordInfoEntity
import ir.alishayanpoor.dictionarywithcaching.domain.model.WordInfo
import ir.alishayanpoor.dictionarywithcaching.domain.repo.WordInfoRepo
import ir.alishayanpoor.dictionarywithcaching.exception.AppException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class WordInfoRepoImpl(
    private val api: DictionaryApi,
    private val dao: WordInfoDao,
) : WordInfoRepo {
    override fun getWordInfo(word: String): Flow<List<WordInfo>> = flow {
        val wordInfoList = dao.getWordInfoList(word).map { it.toWordInfo() }
        emit(wordInfoList)
        try {
            val response = api.getWordInfo(word)
            val remoteWordInfoList = response.body()
            if (response.isSuccessful && remoteWordInfoList != null) {
                dao.deleteWords(remoteWordInfoList.map { it.word })
                dao.insertWordInfoList(remoteWordInfoList.map { it.toWordInfoEntity() })
                emit(remoteWordInfoList.map { it.toWordInfo() })
            }
        } catch (e: HttpException) {
            throw AppException(e.localizedMessage)
        } catch (e: IOException) {
            throw AppException(e.localizedMessage)
        }
    }
}