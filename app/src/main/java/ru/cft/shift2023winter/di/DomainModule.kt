package ru.cft.shift2023winter.di

import org.koin.core.module.Module
import org.koin.dsl.module
import ru.cft.shift2023winter.data.api.RandomUserApi
import ru.cft.shift2023winter.data.converter.RandomUserConverter
import ru.cft.shift2023winter.data.repository.UserRepositoryImpl
import ru.cft.shift2023winter.domain.repository.UserRepository
import ru.cft.shift2023winter.domain.usecase.GetDataRandomUserUseCase
import ru.cft.shift2023winter.domain.usecase.GetUserByIdUseCase
import ru.cft.shift2023winter.domain.usecase.SetToNeedNewUsersUseCase

private fun provideLoanRepository(randomUserApi: RandomUserApi, converter: RandomUserConverter): UserRepository =
    UserRepositoryImpl(randomUserApi, converter)

fun provideDomainModule(): Module =
    module {
        single { provideLoanRepository(randomUserApi = get(), converter = get()) }

        factory { GetDataRandomUserUseCase(repository = get()) }
        factory { GetUserByIdUseCase(repository = get()) }
        factory { SetToNeedNewUsersUseCase(repository = get()) }
    }