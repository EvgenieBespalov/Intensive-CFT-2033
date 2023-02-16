package ru.cft.shift2023winter.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import ru.cft.shift2023winter.domain.usecase.SetToNeedNewUsersUseCase
import ru.cft.shift2023winter.presentation.UserDetailsViewModel
import ru.cft.shift2023winter.presentation.UserListViewModel
import ru.cft.shift2023winter.presentation.FilterViewModel

fun providePresentationModule(): Module =
    module {
        viewModel { arguments ->
            UserDetailsViewModel(
                getUserByIdUseCase = get(),
                userIdName = arguments.get(),
            )
        }

        viewModel {
            UserListViewModel(getDataRandomUserUseCase = get())
        }

        viewModel {
            FilterViewModel(setToNeedNewUsersUseCase = get())
        }
    }