package org.tsoka.eventcity.general.ticket

import io.reactivex.Flowable
import io.reactivex.Single
import java.util.Date
import org.tsoka.eventcity.general.discount.DiscountApi
import org.tsoka.eventcity.general.discount.DiscountCode
import org.tsoka.eventcity.general.event.EventUtils

class TicketService(
    private val ticketApi: TicketApi,
    private val ticketDao: TicketDao,
    private val discountApi: DiscountApi
) {

    fun getTickets(id: Long): Flowable<List<Ticket>> {
        val ticketFlowable = ticketDao.getTicketsForEvent(id)
        return ticketFlowable.switchMap {
            if (it.isNotEmpty())
                ticketFlowable
            else
                ticketApi.getTickets(id)
                        .map {
                            ticketDao.insertTickets(it)
                        }
                        .flatMap {
                            ticketFlowable
                        }
        }
    }

    fun syncTickets(id: Long): Flowable<List<Ticket>> {
        return ticketApi.getTickets(id).map {
            ticketDao.insertTickets(it)
            it
        }
    }

    fun getTicketPriceRange(id: Long): Single<TicketPriceRange> {
        return ticketDao.getTicketPriceRange(id)
    }

    fun getTicketDetails(id: Long): Single<Ticket> {
        return ticketDao.getTicketDetails(id)
    }

    fun getTicketsWithIds(ids: List<Int>): Single<List<Ticket>> {
        return ticketDao.getTicketsWithIds(ids)
    }

    fun getDiscountCode(eventId: Long, code: String): Single<DiscountCode> {
        val filter = "[{\"name\":\"is-active\",\"op\":\"like\",\"val\":\"true\"}," +
            "{\"name\":\"valid-from\",\"op\":\"<\",\"val\":\"%${EventUtils.getTimeInISO8601(Date())}%\"}," +
            "{\"name\":\"valid-till\",\"op\":\">\",\"val\":\"%${EventUtils.getTimeInISO8601(Date())}%\"}]"
        return discountApi.getDiscountCodes(eventId, code, filter)
    }
}
