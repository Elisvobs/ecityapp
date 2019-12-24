package org.tsoka.eventcity.general

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import org.tsoka.eventcity.general.attendees.Attendee
import org.tsoka.eventcity.general.attendees.AttendeeConverter
import org.tsoka.eventcity.general.attendees.AttendeeDao
import org.tsoka.eventcity.general.attendees.ListAttendeeConverter
import org.tsoka.eventcity.general.attendees.forms.CustomForm
import org.tsoka.eventcity.general.auth.User
import org.tsoka.eventcity.general.auth.UserDao
import org.tsoka.eventcity.general.auth.UserIdConverter
import org.tsoka.eventcity.general.event.Event
import org.tsoka.eventcity.general.event.EventDao
import org.tsoka.eventcity.general.event.EventIdConverter
import org.tsoka.eventcity.general.event.subtopic.EventSubTopicConverter
import org.tsoka.eventcity.general.event.tax.Tax
import org.tsoka.eventcity.general.event.tax.TaxDao
import org.tsoka.eventcity.general.event.topic.EventTopic
import org.tsoka.eventcity.general.event.topic.EventTopicConverter
import org.tsoka.eventcity.general.event.topic.EventTopicsDao
import org.tsoka.eventcity.general.event.types.EventTypeConverter
import org.tsoka.eventcity.general.feedback.Feedback
import org.tsoka.eventcity.general.feedback.FeedbackDao
import org.tsoka.eventcity.general.notification.Notification
import org.tsoka.eventcity.general.notification.NotificationDao
import org.tsoka.eventcity.general.order.Order
import org.tsoka.eventcity.general.order.OrderDao
import org.tsoka.eventcity.general.sessions.Session
import org.tsoka.eventcity.general.sessions.SessionDao
import org.tsoka.eventcity.general.sessions.microlocation.MicroLocationConverter
import org.tsoka.eventcity.general.sessions.sessiontype.SessionTypeConverter
import org.tsoka.eventcity.general.sessions.track.TrackConverter
import org.tsoka.eventcity.general.settings.Settings
import org.tsoka.eventcity.general.settings.SettingsDao
import org.tsoka.eventcity.general.social.SocialLink
import org.tsoka.eventcity.general.social.SocialLinksDao
import org.tsoka.eventcity.general.speakercall.Proposal
import org.tsoka.eventcity.general.speakercall.SpeakersCall
import org.tsoka.eventcity.general.speakercall.SpeakersCallConverter
import org.tsoka.eventcity.general.speakercall.SpeakersCallDao
import org.tsoka.eventcity.general.speakers.ListSpeakerIdConverter
import org.tsoka.eventcity.general.speakers.Speaker
import org.tsoka.eventcity.general.speakers.SpeakerDao
import org.tsoka.eventcity.general.speakers.SpeakerWithEvent
import org.tsoka.eventcity.general.speakers.SpeakerWithEventDao
import org.tsoka.eventcity.general.sponsor.Sponsor
import org.tsoka.eventcity.general.sponsor.SponsorDao
import org.tsoka.eventcity.general.sponsor.SponsorWithEvent
import org.tsoka.eventcity.general.sponsor.SponsorWithEventDao
import org.tsoka.eventcity.general.ticket.Ticket
import org.tsoka.eventcity.general.ticket.TicketDao
import org.tsoka.eventcity.general.ticket.TicketIdConverter

@Database(entities = [Event::class, User::class, SocialLink::class, Ticket::class, Attendee::class,
    EventTopic::class, Order::class, CustomForm::class, Speaker::class, SpeakerWithEvent::class, Sponsor::class,
    SponsorWithEvent::class, Session::class, SpeakersCall::class, Feedback::class, Notification::class,
    Settings::class, Proposal::class, Tax::class], version = 9)
@TypeConverters(EventIdConverter::class, EventTopicConverter::class, EventTypeConverter::class,
    EventSubTopicConverter::class, TicketIdConverter::class, MicroLocationConverter::class, UserIdConverter::class,
    AttendeeConverter::class, ListAttendeeConverter::class, SessionTypeConverter::class, TrackConverter::class,
    SpeakersCallConverter::class, ListSpeakerIdConverter::class)
abstract class OpenEventDatabase : RoomDatabase() {

    abstract fun eventDao(): EventDao

    abstract fun userDao(): UserDao

    abstract fun ticketDao(): TicketDao

    abstract fun socialLinksDao(): SocialLinksDao

    abstract fun attendeeDao(): AttendeeDao

    abstract fun speakerDao(): SpeakerDao

    abstract fun speakerWithEventDao(): SpeakerWithEventDao

    abstract fun eventTopicsDao(): EventTopicsDao

    abstract fun orderDao(): OrderDao

    abstract fun sponsorDao(): SponsorDao

    abstract fun sponsorWithEventDao(): SponsorWithEventDao

    abstract fun sessionDao(): SessionDao

    abstract fun speakersCallDao(): SpeakersCallDao

    abstract fun feedbackDao(): FeedbackDao

    abstract fun notificationDao(): NotificationDao

    abstract fun settingsDao(): SettingsDao

    abstract fun taxDao(): TaxDao
}
