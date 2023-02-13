package com.yat3s.openai.api.repository

import android.util.Log
import com.google.gson.Gson
import com.yat3s.openai.api.Config
import com.yat3s.openai.api.model.TextCompletionApiRequestBody
import com.yat3s.openai.api.model.TextCompletionApiResponse
import com.yat3s.openai.api.model.TextCompletionError
import com.yat3s.openai.api.service.OpenAIService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

internal object OpenAIRepository {
    private const val TAG = "OpenAIRepository"

    suspend fun textCompletion(
        apiKey: String,
        requestBody: TextCompletionApiRequestBody,
    ): TextCompletionApiResponse? {
        val okHttpClient: OkHttpClient = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()
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

        if (!response.isSuccessful) {
            var textCompletionApiResponse: TextCompletionApiResponse?

            try {
                val errorString = response.errorBody()?.string()
                textCompletionApiResponse =
                    Gson().fromJson(errorString, TextCompletionApiResponse::class.java)
            } catch (e: Exception) {
                textCompletionApiResponse =
                    TextCompletionApiResponse(error = TextCompletionError(message = "Unexpected exception"))
                Log.e(TAG, "Unexpected exception for textCompletion: $e")
            }

            return textCompletionApiResponse
        }

        return response.body()
    }
}