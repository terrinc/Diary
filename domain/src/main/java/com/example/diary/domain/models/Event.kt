package com.example.diary.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
class Event(
    @SerialName("id") val id: Int,
    @SerialName("date_start") val dateStart: Long,
    @SerialName("date_finish") val dateFinish: Long,
    @SerialName("name") val name: String,
    @SerialName("description") val description: String
) {

    fun getStartDate(): Date = Date(dateStart * 1000L)

    fun getFinishDate(): Date = Date(dateFinish * 1000L)

}
