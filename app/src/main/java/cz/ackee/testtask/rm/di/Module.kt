package cz.ackee.testtask.rm.di

import cz.ackee.testtask.rm.app.Variables
import cz.ackee.testtask.rm.feature.list.presentation.ListAllCharactersViewModel
import cz.ackee.testtask.rm.repository.list.data.repository.AllCharactersRepositoryImpl
import cz.ackee.testtask.rm.repository.list.domain.repository.AllCharactersRepository
import cz.ackee.testtask.rm.repository.list.domain.usecase.GetAllCharactersUseCase
import cz.ackee.testtask.rm.repository.list.domain.usecase.GetAllCharactersUseCaseImpl
import okhttp3.OkHttpClient
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
        single<AllCharactersRepository> { AllCharactersRepositoryImpl(rickMortyApiRetrofit) }
        single<GetAllCharactersUseCase> { GetAllCharactersUseCaseImpl(get()) }

        viewModel { ListAllCharactersViewModel() }
    }
}