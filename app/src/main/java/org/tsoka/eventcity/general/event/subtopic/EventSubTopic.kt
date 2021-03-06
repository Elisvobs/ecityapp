package org.tsoka.eventcity.general.event.subtopic

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.github.jasminb.jsonapi.LongIdHandler
import com.github.jasminb.jsonapi.annotations.Id
import com.github.jasminb.jsonapi.annotations.Relationship
import com.github.jasminb.jsonapi.annotations.Type
import org.tsoka.eventcity.general.event.Event
import org.tsoka.eventcity.general.event.EventId

@Type("event-sub-topic")
@Entity(foreignKeys = [(ForeignKey(entity = Event::class, parentColumns = ["id"],
    childColumns = ["event"], onDelete = ForeignKey.CASCADE))])
data class EventSubTopic(
    @Id(LongIdHandler::class)
    @PrimaryKey
    val id: Long,
    val name: String,
    val slug: String,
    @ColumnInfo(index = true)
    @Relationship("event")
    var event: EventId? = null
)
