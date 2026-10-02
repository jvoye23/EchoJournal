package com.jvcodingsolutions.echojournal.core.database.echo_topic_relation

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.Relation
import com.jvcodingsolutions.echojournal.core.database.echo.EchoEntity
import com.jvcodingsolutions.echojournal.core.database.topic.TopicEntity


// Since one EchoEntity can have many topics, and one topic can have many EchoEntities,
// we have a many-to-many relationship between EchoEntity and Topic.

@Entity(
    primaryKeys = ["echoId", "topic"]
)
data class EchoTopicCrossRef(
    val echoId: Int,
    val topic: String
)

data class EchoWithTopics(
    @Embedded val echo: EchoEntity,
    @Relation(
        parentColumn = "echoId",
        entityColumn = "topic",
        associateBy = Junction(EchoTopicCrossRef::class)
    )
    val topics: List<TopicEntity>
)
