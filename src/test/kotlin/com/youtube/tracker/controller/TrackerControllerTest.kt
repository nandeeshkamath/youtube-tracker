package com.youtube.tracker.controller

import com.youtube.tracker.BaseTest
import com.youtube.tracker.Util.assertTelegramGetChannelInfo
import com.youtube.tracker.Util.assertTelegramSendMessageCall
import com.youtube.tracker.Util.assertYoutubeSearchCall
import com.youtube.tracker.Util.compareJson
import com.youtube.tracker.response.ExpectedResponses
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

class TrackerControllerTest : BaseTest() {
    @Test
    fun `given track request when search result contains keyword then message sent`() {
        val response = mockMvc.perform(
            MockMvcRequestBuilders.post("/track")
                .content(stubTrackerRequest())
                .contentType(MediaType.APPLICATION_JSON)
                .with(basicAuth))
            .andExpect(MockMvcResultMatchers.status().isAccepted)
            .andReturn().response.contentAsString

        compareJson(ExpectedResponses.success, response)
        assertTelegramGetChannelInfo()
        assertYoutubeSearchCall()
        assertTelegramSendMessageCall(2)
    }

//    @Test
//    fun `given track request when channel not found then bad request`() {
//        stubTelegramGetChatInfo(telegramGetChatNotFoundJson())
//        val response = mockMvc.perform(
//            MockMvcRequestBuilders.post("/track")
//                .content(stubTrackerRequest())
//                .contentType(MediaType.APPLICATION_JSON)
//                .with(basicAuth))
//            .andExpect(MockMvcResultMatchers.status().isAccepted)
//            .andReturn().response.contentAsString
//
//        compareJson(ExpectedResponses.success, response)
//        assertTelegramGetChannelInfo()
//        assertYoutubeSearchCall(0)
//        assertTelegramSendMessageCall(0)
//    }
}