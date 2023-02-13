package com.yat3s.openai.api.service

import com.yat3s.openai.api.Config
import com.yat3s.openai.api.model.TextCompletionApiRequestBody
import com.yat3s.openai.api.model.TextCompletionApiResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

internal interface OpenAIService {
    @Headers(
        "content-type: application/json",
    )
    @POST(Config.URL_TEXT_COMPLETION)
    suspend fun textCompletion(
        @Header("Authorization") authorization: String,
        @Body requestBody: TextCompletionApiRequestBody
    ): Response<TextCompletionApiResponse>
}