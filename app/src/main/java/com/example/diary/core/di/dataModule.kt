package com.example.diary.core.di

import com.example.diary.data.repository.EventRepositoryImp
import com.example.diary.domain.repository.EventRepository
import org.koin.dsl.module

val dataModule = module{
    single<EventRepository> {
        EventRepositoryImp(context = get())
    }
}
