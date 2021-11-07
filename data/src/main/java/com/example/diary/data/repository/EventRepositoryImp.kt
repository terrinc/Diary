package com.example.diary.data.repository

import android.content.Context
import com.example.diary.domain.models.Event
import com.example.diary.domain.repository.EventRepository
import com.example.diary.utils.readAssetFileToString
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class EventRepositoryImp(private val context: Context) : EventRepository {
    override suspend fun getAllEvents(): List<Event> {
        val data = readAssetFileToString(context, "fake_events.json")
        return Json.decodeFromString(data)
    }
}
