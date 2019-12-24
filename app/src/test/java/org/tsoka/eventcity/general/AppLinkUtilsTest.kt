package org.tsoka.eventcity.general

import org.tsoka.eventcity.general.utils.AppLinkData
import org.tsoka.eventcity.general.utils.AppLinkUtils
import org.tsoka.eventcity.general.utils.EVENT_IDENTIFIER
import org.tsoka.eventcity.general.utils.RESET_PASSWORD_TOKEN
import org.tsoka.eventcity.general.utils.VERIFICATION_TOKEN
import org.junit.Assert.assertEquals
import org.junit.Test

class AppLinkUtilsTest {

    @Test
    fun `should get event link`() {
        val uri = "https://theeventcity.com/e/5f6d3feb"
        assertEquals(AppLinkData(R.id.eventDetailsFragment,
            EVENT_IDENTIFIER,
            "5f6d3feb"), AppLinkUtils.getData(uri))
    }

    @Test
    fun `should get reset password link`() {
        val uri = "https://theeventcity.com/reset-password?token=822980340478781748445098077144"
        assertEquals(AppLinkData(R.id.eventsFragment,
            RESET_PASSWORD_TOKEN,
            "822980340478781748445098077144"), AppLinkUtils.getData(uri))
    }

    @Test
    fun `should get verify email link`() {
        val uri = "https://theeventcity.com/verify?token=WyJsaXZlLmhhcnNoaXRAaG"
        assertEquals(AppLinkData(R.id.profileFragment,
            VERIFICATION_TOKEN,
            "WyJsaXZlLmhhcnNoaXRAaG"), AppLinkUtils.getData(uri))
    }
}
