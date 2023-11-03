package cz.ackee.testtask.rm.di

import androidx.room.Room
import cz.ackee.testtask.rm.app.Variables
import cz.ackee.testtask.rm.feature.detail.presentation.CharacterDetailViewModel
import cz.ackee.testtask.rm.feature.favorite.presentation.FavoriteCharactersViewModel
import cz.ackee.testtask.rm.feature.list.presentation.AllCharactersViewModel
import cz.ackee.testtask.rm.repository.common.data.repository.CharactersRepositoryImpl
import cz.ackee.testtask.rm.repository.common.domain.repository.CharactersRepository
import cz.ackee.testtask.rm.repository.detail.domain.usecase.AreCharactersFavoriteUseCase
import cz.ackee.testtask.rm.repository.detail.domain.usecase.AreCharactersFavoriteUseCaseImpl
import cz.ackee.testtask.rm.repository.detail.domain.usecase.GetCharacterDetailUseCase
import cz.ackee.testtask.rm.repository.detail.domain.usecase.GetCharacterDetailUseCaseImpl
import cz.ackee.testtask.rm.repository.favorite.data.database.FavoriteCharacterDatabaseImpl
import cz.ackee.testtask.rm.repository.favorite.data.repository.FavoriteCharacterRepositoryImpl
import cz.ackee.testtask.rm.repository.favorite.domain.repository.FavoriteCharacterRepository
import cz.ackee.testtask.rm.repository.favorite.domain.usecase.ChangeCharacterFavUseCase
import cz.ackee.testtask.rm.repository.favorite.domain.usecase.ChangeCharacterFavUseCaseImpl
import cz.ackee.testtask.rm.repository.favorite.domain.usecase.GetFavoriteCharactersUseCase
import cz.ackee.testtask.rm.repository.favorite.domain.usecase.GetFavoriteCharactersUseCaseImpl
import cz.ackee.testtask.rm.repository.list.domain.usecase.GetAllCharactersUseCase
import cz.ackee.testtask.rm.repository.list.domain.usecase.GetAllCharactersUseCaseImpl
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber

object Module {
    private val rickMortyApiRetrofit = Retrofit.Builder()
        .baseUrl(Variables.RM_API_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .client(
            OkHttpClient().newBuilder()
                .addInterceptor {
                    Timber.d("Retrofit - sending request to \"${it.request().url}\"")
                    it.proceed(it.request())
                }
                .build()
        )
        .build()

    val listModule = module {
        single<CharactersRepository> { CharactersRepositoryImpl(rickMortyApiRetrofit) }
        single<GetAllCharactersUseCase> { GetAllCharactersUseCaseImpl(get(), get()) }

        viewModel { AllCharactersViewModel(get()) }
    }

    val detailModule = module {
        single<GetCharacterDetailUseCase> { GetCharacterDetailUseCaseImpl(get(), get()) }

        viewModel { CharacterDetailViewModel(get(), get(), it[0]) }
    }

    val favoriteModule = module {
        single<GetFavoriteCharactersUseCase> { GetFavoriteCharactersUseCaseImpl(get()) }
        single<ChangeCharacterFavUseCase> { ChangeCharacterFavUseCaseImpl(get()) }
        single<AreCharactersFavoriteUseCase> { AreCharactersFavoriteUseCaseImpl(get()) }

//        single<FavoriteCharacterDatabase> {
//            Room.databaseBuilder(
//                context = androidContext(),
//                klass = FavoriteCharacterDatabaseImpl::class.java,
//                name = Variables.DB_NAME
//            ).build()
//        }

        single<FavoriteCharacterRepository> { FavoriteCharacterRepositoryImpl(Room.databaseBuilder(
            context = androidContext(),
            klass = FavoriteCharacterDatabaseImpl::class.java,
            name = Variables.DB_NAME
        ).build()) }

        viewModel { FavoriteCharactersViewModel(get()) }
    }
}