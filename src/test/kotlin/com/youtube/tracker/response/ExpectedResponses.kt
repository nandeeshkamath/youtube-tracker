package com.youtube.tracker.response

object ExpectedResponses {

    //language=JSON
    val success =
        """
            {
              "resultInfo": {
                "id": "000",
                "message": "SUCCESS"
              },
              "data": null
            }
        """.trimIndent()

    //language=JSON
    val chatNotFound =
        """
            {
              "resultInfo": {
                "id": "001",
                "message": "BAD_REQUEST"
              },
              "data": "Bad Request: chat not found"
            }
        """.trimIndent()
}
