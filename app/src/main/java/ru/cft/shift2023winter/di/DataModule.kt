package ru.cft.shift2023winter.di

import org.koin.core.module.Module
import org.koin.dsl.module
import ru.cft.shift2023winter.data.converter.RandomUserConverter

fun provideDataModule(): Module =
    module {
        factory { RandomUserConverter() }
    }