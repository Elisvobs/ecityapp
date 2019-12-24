package org.tsoka.eventcity.general.di

import androidx.paging.PagedList
import androidx.room.Room
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.github.jasminb.jsonapi.ResourceConverter
import com.github.jasminb.jsonapi.retrofit.JSONAPIConverterFactory
import java.util.concurrent.TimeUnit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.tsoka.eventcity.general.BuildConfig
import org.tsoka.eventcity.general.OpenEventDatabase
import org.tsoka.eventcity.general.StartupViewModel
import org.tsoka.eventcity.general.about.AboutEventViewModel
import org.tsoka.eventcity.general.attendees.Attendee
import org.tsoka.eventcity.general.attendees.AttendeeApi
import org.tsoka.eventcity.general.attendees.AttendeeService
import org.tsoka.eventcity.general.attendees.AttendeeViewModel
import org.tsoka.eventcity.general.attendees.forms.CustomForm
import org.tsoka.eventcity.general.auth.AuthApi
import org.tsoka.eventcity.general.auth.AuthHolder
import org.tsoka.eventcity.general.auth.AuthService
import org.tsoka.eventcity.general.auth.AuthViewModel
import org.tsoka.eventcity.general.auth.EditProfileViewModel
import org.tsoka.eventcity.general.auth.LoginViewModel
import org.tsoka.eventcity.general.auth.ProfileViewModel
import org.tsoka.eventcity.general.auth.RequestAuthenticator
import org.tsoka.eventcity.general.auth.SignUp
import org.tsoka.eventcity.general.auth.SignUpViewModel
import org.tsoka.eventcity.general.auth.SmartAuthViewModel
import org.tsoka.eventcity.general.auth.User
import org.tsoka.eventcity.general.connectivity.MutableConnectionLiveData
import org.tsoka.eventcity.general.data.Network
import org.tsoka.eventcity.general.data.Preference
import org.tsoka.eventcity.general.data.Resource
import org.tsoka.eventcity.general.discount.DiscountApi
import org.tsoka.eventcity.general.discount.DiscountCode
import org.tsoka.eventcity.general.event.Event
import org.tsoka.eventcity.general.event.EventApi
import org.tsoka.eventcity.general.event.EventDetailsViewModel
import org.tsoka.eventcity.general.event.EventId
import org.tsoka.eventcity.general.event.EventService
import org.tsoka.eventcity.general.event.EventsViewModel
import org.tsoka.eventcity.general.event.faq.EventFAQ
import org.tsoka.eventcity.general.event.faq.EventFAQApi
import org.tsoka.eventcity.general.event.faq.EventFAQViewModel
import org.tsoka.eventcity.general.event.location.EventLocation
import org.tsoka.eventcity.general.event.location.EventLocationApi
import org.tsoka.eventcity.general.event.subtopic.EventSubTopic
import org.tsoka.eventcity.general.event.tax.Tax
import org.tsoka.eventcity.general.event.tax.TaxApi
import org.tsoka.eventcity.general.event.tax.TaxService
import org.tsoka.eventcity.general.event.topic.EventTopic
import org.tsoka.eventcity.general.event.topic.EventTopicApi
import org.tsoka.eventcity.general.event.types.EventType
import org.tsoka.eventcity.general.event.types.EventTypesApi
import org.tsoka.eventcity.general.favorite.FavoriteEvent
import org.tsoka.eventcity.general.favorite.FavoriteEventApi
import org.tsoka.eventcity.general.favorite.FavoriteEventsViewModel
import org.tsoka.eventcity.general.feedback.Feedback
import org.tsoka.eventcity.general.feedback.FeedbackApi
import org.tsoka.eventcity.general.feedback.FeedbackService
import org.tsoka.eventcity.general.feedback.FeedbackViewModel
import org.tsoka.eventcity.general.notification.Notification
import org.tsoka.eventcity.general.notification.NotificationApi
import org.tsoka.eventcity.general.notification.NotificationService
import org.tsoka.eventcity.general.notification.NotificationViewModel
import org.tsoka.eventcity.general.order.Charge
import org.tsoka.eventcity.general.order.ConfirmOrder
import org.tsoka.eventcity.general.order.Order
import org.tsoka.eventcity.general.order.OrderApi
import org.tsoka.eventcity.general.order.OrderCompletedViewModel
import org.tsoka.eventcity.general.order.OrderDetailsViewModel
import org.tsoka.eventcity.general.order.OrderService
import org.tsoka.eventcity.general.order.OrdersUnderUserViewModel
import org.tsoka.eventcity.general.paypal.Paypal
import org.tsoka.eventcity.general.paypal.PaypalApi
import org.tsoka.eventcity.general.search.SearchResultsViewModel
import org.tsoka.eventcity.general.search.SearchViewModel
import org.tsoka.eventcity.general.search.location.GeoLocationViewModel
import org.tsoka.eventcity.general.search.location.LocationService
import org.tsoka.eventcity.general.search.location.LocationServiceImpl
import org.tsoka.eventcity.general.search.location.SearchLocationViewModel
import org.tsoka.eventcity.general.search.time.SearchTimeViewModel
import org.tsoka.eventcity.general.search.type.SearchTypeViewModel
import org.tsoka.eventcity.general.sessions.Session
import org.tsoka.eventcity.general.sessions.SessionApi
import org.tsoka.eventcity.general.sessions.SessionService
import org.tsoka.eventcity.general.sessions.SessionViewModel
import org.tsoka.eventcity.general.sessions.microlocation.MicroLocation
import org.tsoka.eventcity.general.sessions.sessiontype.SessionType
import org.tsoka.eventcity.general.sessions.track.Track
import org.tsoka.eventcity.general.settings.Settings
import org.tsoka.eventcity.general.settings.SettingsApi
import org.tsoka.eventcity.general.settings.SettingsService
import org.tsoka.eventcity.general.settings.SettingsViewModel
import org.tsoka.eventcity.general.social.SocialLink
import org.tsoka.eventcity.general.social.SocialLinkApi
import org.tsoka.eventcity.general.social.SocialLinksService
import org.tsoka.eventcity.general.speakercall.EditSpeakerViewModel
import org.tsoka.eventcity.general.speakercall.Proposal
import org.tsoka.eventcity.general.speakercall.SpeakersCall
import org.tsoka.eventcity.general.speakercall.SpeakersCallProposalViewModel
import org.tsoka.eventcity.general.speakercall.SpeakersCallViewModel
import org.tsoka.eventcity.general.speakers.Speaker
import org.tsoka.eventcity.general.speakers.SpeakerApi
import org.tsoka.eventcity.general.speakers.SpeakerService
import org.tsoka.eventcity.general.speakers.SpeakerViewModel
import org.tsoka.eventcity.general.sponsor.Sponsor
import org.tsoka.eventcity.general.sponsor.SponsorApi
import org.tsoka.eventcity.general.sponsor.SponsorService
import org.tsoka.eventcity.general.sponsor.SponsorsViewModel
import org.tsoka.eventcity.general.ticket.Ticket
import org.tsoka.eventcity.general.ticket.TicketApi
import org.tsoka.eventcity.general.ticket.TicketId
import org.tsoka.eventcity.general.ticket.TicketService
import org.tsoka.eventcity.general.ticket.TicketsViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory

val commonModule = module {
    single { Preference() }
    single { Network() }
    single { Resource() }
    factory { MutableConnectionLiveData() }
    factory<LocationService> { LocationServiceImpl(androidContext(), get()) }
}

val apiModule = module {
    single {
        val retrofit: Retrofit = get()
        retrofit.create(EventApi::class.java)
    }
    single {
        val retrofit: Retrofit = get()
        retrofit.create(AuthApi::class.java)
    }
    single {
        val retrofit: Retrofit = get()
        retrofit.create(TicketApi::class.java)
    }
    single {
        val retrofit: Retrofit = get()
        retrofit.create(FavoriteEventApi::class.java)
    }
    single {
        val retrofit: Retrofit = get()
        retrofit.create(SocialLinkApi::class.java)
    }
    single {
        val retrofit: Retrofit = get()
        retrofit.create(EventTopicApi::class.java)
    }
    single {
        val retrofit: Retrofit = get()
        retrofit.create(AttendeeApi::class.java)
    }
    single {
        val retrofit: Retrofit = get()
        retrofit.create(OrderApi::class.java)
    }
    single {
        val retrofit: Retrofit = get()
        retrofit.create(PaypalApi::class.java)
    }
    single {
        val retrofit: Retrofit = get()
        retrofit.create(EventTypesApi::class.java)
    }
    single {
        val retrofit: Retrofit = get()
        retrofit.create(EventLocationApi::class.java)
    }
    single {
        val retrofit: Retrofit = get()
        retrofit.create(FeedbackApi::class.java)
    }
    single {
        val retrofit: Retrofit = get()
        retrofit.create(SpeakerApi::class.java)
    }
    single {
        val retrofit: Retrofit = get()
        retrofit.create(EventFAQApi::class.java)
    }
    single {
        val retrofit: Retrofit = get()
        retrofit.create(SessionApi::class.java)
    }
    single {
        val retrofit: Retrofit = get()
        retrofit.create(SponsorApi::class.java)
    }
    single {
        val retrofit: Retrofit = get()
        retrofit.create(NotificationApi::class.java)
    }
    single {
        val retrofit: Retrofit = get()
        retrofit.create(DiscountApi::class.java)
    }
    single {
        val retrofit: Retrofit = get()
        retrofit.create(SettingsApi::class.java)
    }
    single {
        val retrofit: Retrofit = get()
        retrofit.create(TaxApi::class.java)
    }

    factory { AuthHolder(get()) }
    factory { AuthService(get(), get(), get(), get(), get(), get(), get()) }

    factory { EventService(get(), get(), get(), get(), get(), get(), get(), get(), get()) }
    factory { SpeakerService(get(), get(), get()) }
    factory { SponsorService(get(), get(), get()) }
    factory { TicketService(get(), get(), get()) }
    factory { SocialLinksService(get(), get()) }
    factory { AttendeeService(get(), get(), get()) }
    factory { OrderService(get(), get(), get(), get(), get()) }
    factory { SessionService(get(), get()) }
    factory { NotificationService(get(), get()) }
    factory { FeedbackService(get(), get()) }
    factory { SettingsService(get(), get()) }
    factory { TaxService(get(), get()) }
}

val viewModelModule = module {
    viewModel { LoginViewModel(get(), get(), get(), get()) }
    viewModel { EventsViewModel(get(), get(), get(), get(), get(), get()) }
    viewModel { StartupViewModel(get(), get(), get(), get(), get(), get()) }
    viewModel { ProfileViewModel(get(), get()) }
    viewModel { SignUpViewModel(get(), get(), get()) }
    viewModel {
        EventDetailsViewModel(get(), get(), get(), get(), get(), get(), get(), get(), get(), get(), get(), get()) }
    viewModel { SessionViewModel(get(), get(), get()) }
    viewModel { SearchViewModel(get(), get()) }
    viewModel { SearchResultsViewModel(get(), get(), get(), get(), get(), get()) }
    viewModel { AttendeeViewModel(get(), get(), get(), get(), get(), get(), get(), get()) }
    viewModel { SearchLocationViewModel(get(), get(), get()) }
    viewModel { SearchTimeViewModel(get()) }
    viewModel { SearchTypeViewModel(get(), get(), get()) }
    viewModel { TicketsViewModel(get(), get(), get(), get(), get(), get()) }
    viewModel { AboutEventViewModel(get(), get()) }
    viewModel { EventFAQViewModel(get(), get()) }
    viewModel { FavoriteEventsViewModel(get(), get(), get()) }
    viewModel { SettingsViewModel(get(), get(), get()) }
    viewModel { OrderCompletedViewModel(get(), get(), get(), get()) }
    viewModel { OrdersUnderUserViewModel(get(), get(), get(), get(), get()) }
    viewModel { OrderDetailsViewModel(get(), get(), get(), get()) }
    viewModel { EditProfileViewModel(get(), get(), get()) }
    viewModel { GeoLocationViewModel(get()) }
    viewModel { SmartAuthViewModel() }
    viewModel { SpeakerViewModel(get(), get()) }
    viewModel { SponsorsViewModel(get(), get()) }
    viewModel { NotificationViewModel(get(), get(), get(), get()) }
    viewModel { AuthViewModel(get(), get(), get()) }
    viewModel { SpeakersCallViewModel(get(), get(), get(), get(), get(), get()) }
    viewModel { SpeakersCallProposalViewModel(get(), get(), get(), get(), get()) }
    viewModel { EditSpeakerViewModel(get(), get(), get(), get()) }
    viewModel { FeedbackViewModel(get(), get()) }
}

val networkModule = module {

    single {
        val objectMapper = jacksonObjectMapper()
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        objectMapper
    }

    single {
        PagedList
            .Config
            .Builder()
            .setPageSize(5)
            .setInitialLoadSizeHint(5)
            .setEnablePlaceholders(false)
            .build()
    }

    single {
        val connectTimeout = 15 // 15s
        val readTimeout = 15 // 15s

        val builder = OkHttpClient().newBuilder()
            .connectTimeout(connectTimeout.toLong(), TimeUnit.SECONDS)
            .readTimeout(readTimeout.toLong(), TimeUnit.SECONDS)
            .addInterceptor(HostSelectionInterceptor(get()))
            .addInterceptor(RequestAuthenticator(get()))
            .addNetworkInterceptor(StethoInterceptor())

        if (BuildConfig.DEBUG) {
            val httpLoggingInterceptor = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
            builder.addInterceptor(httpLoggingInterceptor)
        }
        builder.build()
    }

    single {
        val baseUrl = BuildConfig.DEFAULT_BASE_URL
        val objectMapper: ObjectMapper = get()
        val onlineApiResourceConverter = ResourceConverter(
            objectMapper, Event::class.java, User::class.java,
            SignUp::class.java, Ticket::class.java, SocialLink::class.java, EventId::class.java,
            EventTopic::class.java, Attendee::class.java, TicketId::class.java, Order::class.java,
            Charge::class.java, Paypal::class.java, ConfirmOrder::class.java,
            CustomForm::class.java, EventLocation::class.java, EventType::class.java,
            EventSubTopic::class.java, Feedback::class.java, Speaker::class.java, FavoriteEvent::class.java,
            Session::class.java, SessionType::class.java, MicroLocation::class.java, SpeakersCall::class.java,
            Sponsor::class.java, EventFAQ::class.java, Notification::class.java, Track::class.java,
            DiscountCode::class.java, Settings::class.java, Proposal::class.java, Tax::class.java)

        Retrofit.Builder()
            .client(get())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(JSONAPIConverterFactory(onlineApiResourceConverter))
            .addConverterFactory(JacksonConverterFactory.create(objectMapper))
            .baseUrl(baseUrl)
            .build()
    }
}

val databaseModule = module {

    single {
        Room.databaseBuilder(androidApplication(),
            OpenEventDatabase::class.java, "open_event_database")
            .fallbackToDestructiveMigration()
            .build()
    }

    factory {
        val database: OpenEventDatabase = get()
        database.eventDao()
    }

    factory {
        val database: OpenEventDatabase = get()
        database.sessionDao()
    }

    factory {
        val database: OpenEventDatabase = get()
        database.userDao()
    }

    factory {
        val database: OpenEventDatabase = get()
        database.ticketDao()
    }

    factory {
        val database: OpenEventDatabase = get()
        database.socialLinksDao()
    }

    factory {
        val database: OpenEventDatabase = get()
        database.attendeeDao()
    }

    factory {
        val database: OpenEventDatabase = get()
        database.eventTopicsDao()
    }

    factory {
        val database: OpenEventDatabase = get()
        database.orderDao()
    }
    factory {
        val database: OpenEventDatabase = get()
        database.speakerWithEventDao()
    }
    factory {
        val database: OpenEventDatabase = get()
        database.speakerDao()
    }

    factory {
        val database: OpenEventDatabase = get()
        database.sponsorWithEventDao()
    }

    factory {
        val database: OpenEventDatabase = get()
        database.sponsorDao()
    }

    factory {
        val database: OpenEventDatabase = get()
        database.feedbackDao()
    }

    factory {
        val database: OpenEventDatabase = get()
        database.speakersCallDao()
    }

    factory {
        val database: OpenEventDatabase = get()
        database.notificationDao()
    }

    factory {
        val database: OpenEventDatabase = get()
        database.settingsDao()
    }

    factory {
        val database: OpenEventDatabase = get()
        database.taxDao()
    }
}
