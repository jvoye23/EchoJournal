package com.jvcodingsolutions.echojournal.echos.data.echo

import com.jvcodingsolutions.echojournal.core.database.echo.EchoEntity
import com.jvcodingsolutions.echojournal.core.database.echo_topic_relation.EchoWithTopics
import com.jvcodingsolutions.echojournal.core.database.topic.TopicEntity
import com.jvcodingsolutions.echojournal.echos.domain.echo.Echo
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Instant

fun EchoWithTopics.toEcho(): Echo {
    return Echo(
        mood = echo.mood,
        title = echo.title,
        note = echo.note,
        topics = topics.map { it.topic },
        audioFilePath = echo.audioFilePath,
        audioPlaybackLength = echo.audioPlaybackLength.milliseconds,
        audioAmplitudes = echo.audioAmplitudes,
        recordedAt = Instant.fromEpochMilliseconds(echo.recordedAt),
        id = echo.echoId
    )
}

fun Echo.toEchoWithTopics(): EchoWithTopics {
    return EchoWithTopics(
        echo = EchoEntity(
            echoId = id ?: 0,
            title = title,
            mood = mood,
            recordedAt = recordedAt.toEpochMilliseconds(),
            note = note,
            audioAmplitudes = audioAmplitudes,
            audioFilePath = audioFilePath,
            audioPlaybackLength = audioPlaybackLength.inWholeMilliseconds
        ),
        topics = topics.map { TopicEntity(it) }
    )
}