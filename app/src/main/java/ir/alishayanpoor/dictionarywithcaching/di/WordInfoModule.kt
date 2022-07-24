package ir.alishayanpoor.dictionarywithcaching.di

import android.app.Application
import androidx.room.Room
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.alishayanpoor.dictionarywithcaching.data.local.Converters
import ir.alishayanpoor.dictionarywithcaching.data.local.WordInfoDatabase
import ir.alishayanpoor.dictionarywithcaching.data.remote.DictionaryApi
import ir.alishayanpoor.dictionarywithcaching.data.repo.WordInfoRepoImpl
import ir.alishayanpoor.dictionarywithcaching.domain.repo.WordInfoRepo
import ir.alishayanpoor.dictionarywithcaching.domain.use_case.WordInfoUseCase
import ir.alishayanpoor.dictionarywithcaching.util.GsonParser
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WordInfoModule {

    @Provides
    @Singleton
    fun provideDictionaryApi(): DictionaryApi {
        return Retrofit.Builder()
            .baseUrl(DictionaryApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DictionaryApi::class.java)
    }

    @Provides
    @Singleton
    fun provideWordInfoDatabase(app: Application): WordInfoDatabase {
        return Room.databaseBuilder(app, WordInfoDatabase::class.java, "word_db")
            .addTypeConverter(Converters(GsonParser(Gson())))
            .build()
    }

    @Provides
    @Singleton
    fun provideWordInfoRepo(
        api: DictionaryApi,
        db: WordInfoDatabase,
    ): WordInfoRepo {
        return WordInfoRepoImpl(api, db.dao)
    }

    @Provides
    @Singleton
    fun provideWordInfoUseCase(
        repo: WordInfoRepo,
    ): WordInfoUseCase {
        return WordInfoUseCase(repo)
    }
}