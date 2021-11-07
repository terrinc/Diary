package com.example.diary.domain.repository

import com.example.diary.domain.models.Event

interface EventRepository {
    suspend fun getAllEvents(): List<Event>
}
