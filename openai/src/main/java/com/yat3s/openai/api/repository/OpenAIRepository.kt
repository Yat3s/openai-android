package com.yat3s.openai.api.repository

import com.yat3s.openai.api.Config
import com.yat3s.openai.api.model.TextCompletionApiRequestBody
import com.yat3s.openai.api.model.TextCompletionApiResponse
import com.yat3s.openai.api.service.OpenAIService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

internal object OpenAIRepository {
    suspend fun textCompletion(
        apiKey: String,
        requestBody: TextCompletionApiRequestBody,
    ): TextCompletionApiResponse? {
        val okHttpClient: OkHttpClient = OkHttpClient.Builder().build()
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(Config.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val apiService = retrofit.create(OpenAIService::class.java)
        val response = apiService.textCompletion(
            "Bearer $apiKey",
            requestBody
        )

        if (response.isSuccessful) {
            return response.body()
        }

        return null
    }
}