package org.tsoka.eventcity.general.sponsor

object SponsorUtil {
    fun sortSponsorByLevel(sponsors: List<Sponsor>): List<Sponsor> {
        return sponsors.sortedWith(compareBy(Sponsor::level))
    }
}
