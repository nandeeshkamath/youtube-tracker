package com.youtube.tracker

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder
import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.stubbing.StubMapping
import com.youtube.tracker.config.WireMockServerInitializer
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import java.util.*

@SpringBootTest(properties = ["spring.profiles.active=test"])
@AutoConfigureMockMvc
@ContextConfiguration(initializers = [WireMockServerInitializer::class])
class BaseTest {

    @Value("\${spring.security.user.name}")
    private lateinit var defaultUserName: String
    @Value("\${spring.security.user.password}")
    private lateinit var defaultUserPassword: String

    @Autowired
    protected lateinit var wireMockServer: WireMockServer

    @Autowired
    protected lateinit var mockMvc: MockMvc

    @BeforeEach
    fun setUp() {
        stubYoutubeSearch()
        stubTelegramSendMessage()
    }

    @AfterEach
    fun tearDown() {
        wireMockServer.resetAll()
    }

    protected val basicAuth by lazy { SecurityMockMvcRequestPostProcessors.httpBasic(defaultUserName, defaultUserPassword) }

    protected val stubAuth: Pair<String, String>
        get() = "$defaultUserName:$defaultUserPassword".let {
            println("response : ${"Authorization" to "Basic ${Base64.getEncoder().encode(it.toByteArray())}"}")
            "Authorization" to "Basic ${Base64.getEncoder().encode(it.toByteArray())}"
        }

    protected fun stubTrackerRequest() =
        """
            {
                "keyword": "trailer",
                "interval": 72,
                "channels": [
                    "UCKy1dAqELo0zrOtPkf0eTMw"
                ]
            }
        """.trimIndent()

    protected fun stubYoutubeSearch(
        response: ResponseDefinitionBuilder = youtubeSearchSuccessJson())
    : StubMapping =
        WireMock.stubFor(WireMock.get(WireMock.urlPathMatching("/v3/search*")).willReturn(response))

    protected fun stubTelegramSendMessage(
        response: ResponseDefinitionBuilder = telegramSendMessageSuccessJson()
    ): StubMapping =
        WireMock.stubFor(WireMock.get(WireMock.urlPathMatching("/sendMessage*")).willReturn(response))

    protected fun youtubeSearchSuccessJson(): ResponseDefinitionBuilder =
        WireMock.okJson(
            """
                {
                	"kind": "youtube#searchListResponse",
                	"etag": "uizub2wnMobXrhHwk0fuW784QR4",
                	"nextPageToken": "CAUQAA",
                	"regionCode": "IN",
                	"pageInfo": {
                		"totalResults": 28,
                		"resultsPerPage": 3
                	},
                	"items": [{
                			"kind": "youtube#searchResult",
                			"etag": "KQjx4m5kG7A73VizB_nKehRIfSw",
                			"id": {
                				"kind": "youtube#video",
                				"videoId": "8D9pPtOgIFQ"
                			},
                			"snippet": {
                				"publishedAt": "2022-01-21T17:00:09Z",
                				"channelId": "UCKy1dAqELo0zrOtPkf0eTMw",
                				"title": "Achilles: Legends Untold - Official Story Trailer",
                				"description": "Get a look at the story trailer for Achilles: Legends Untold, which mixes cinematic scenes and gameplay to give you an idea of ...",
                				"thumbnails": {
                					"default": {
                						"url": "https://i.ytimg.com/vi/8D9pPtOgIFQ/default.jpg",
                						"width": 120,
                						"height": 90
                					},
                					"medium": {
                						"url": "https://i.ytimg.com/vi/8D9pPtOgIFQ/mqdefault.jpg",
                						"width": 320,
                						"height": 180
                					},
                					"high": {
                						"url": "https://i.ytimg.com/vi/8D9pPtOgIFQ/hqdefault.jpg",
                						"width": 480,
                						"height": 360
                					}
                				},
                				"channelTitle": "IGN",
                				"liveBroadcastContent": "none",
                				"publishTime": "2022-01-21T17:00:09Z"
                			}
                		},
                		{
                			"kind": "youtube#searchResult",
                			"etag": "FJYEZ7mqMLsFA6m6AzUlXeaA_qo",
                			"id": {
                				"kind": "youtube#video",
                				"videoId": "l8yuQCTggfQ"
                			},
                			"snippet": {
                				"publishedAt": "2022-01-20T13:00:21Z",
                				"channelId": "UCKy1dAqELo0zrOtPkf0eTMw",
                				"title": "WWE 2K22 - Official Announcement Trailer",
                				"description": "Watch the WWE 2K22 announcement trailer to see how the developers and wrestling stars have joined forces for the upcoming ...",
                				"thumbnails": {
                					"default": {
                						"url": "https://i.ytimg.com/vi/l8yuQCTggfQ/default.jpg",
                						"width": 120,
                						"height": 90
                					},
                					"medium": {
                						"url": "https://i.ytimg.com/vi/l8yuQCTggfQ/mqdefault.jpg",
                						"width": 320,
                						"height": 180
                					},
                					"high": {
                						"url": "https://i.ytimg.com/vi/l8yuQCTggfQ/hqdefault.jpg",
                						"width": 480,
                						"height": 360
                					}
                				},
                				"channelTitle": "IGN",
                				"liveBroadcastContent": "none",
                				"publishTime": "2022-01-20T13:00:21Z"
                			}
                		}
                	]
                }
            """.trimIndent()
        )

    protected fun telegramSendMessageSuccessJson(): ResponseDefinitionBuilder =
        WireMock.okJson(
            """
                {
                	"ok": true,
                	"result": {
                		"message_id": 14,
                		"sender_chat": {
                			"id": -1001591849465,
                			"title": "Trailer Tracker Stg",
                			"username": "trailertrackerstg",
                			"type": "channel"
                		},
                		"chat": {
                			"id": -1001591849465,
                			"title": "Trailer Tracker Stg",
                			"username": "trailertrackerstg",
                			"type": "channel"
                		},
                		"date": 1642919799,
                		"text": "https://youtu.be/l8yuQCTggfQ",
                		"entities": [{
                			"offset": 0,
                			"length": 28,
                			"type": "url"
                		}]
                	}
                }
            """.trimIndent()
        )
}