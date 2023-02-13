package com.yat3s.openai.api.repository

import android.util.Log
import com.google.gson.Gson
import com.yat3s.openai.api.Config
import com.yat3s.openai.api.model.*
import com.yat3s.openai.api.service.OpenAIService
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

internal class OpenAIRepository {

    // TODO, replace with Hilt
    private var apiService: OpenAIService? = null

    suspend fun textCompletion(
        apiKey: String,
        requestBody: TextCompletionApiRequestBody,
    ): TextCompletionApiResponse? {
        getApiService()?.let {
            val response = it.textCompletion(
                "Bearer $apiKey",
                requestBody
            )

            if (!response.isSuccessful) {
                return handleError(response, TextCompletionApiResponse::class.java)
            }

            return response.body()
        }

        return null
    }

    suspend fun imageGeneration(
        apiKey: String,
        requestBody: ImageGenerationApiRequestBody,
    ): ImageGenerationApiResponse? {
        getApiService()?.let {
            val response = it.imageGeneration(
                "Bearer $apiKey",
                requestBody
            )

            if (!response.isSuccessful) {
                return handleError(response, ImageGenerationApiResponse::class.java)
            }

            return response.body()
        }

        return null
    }

    private fun <T> handleError(response: Response<T>, classOfT: Class<T>): T? {
        return try {
            val errorString = response.errorBody()?.string()
            Gson().fromJson(errorString, classOfT)
        } catch (e: Exception) {
            Log.e(TAG, "textCompletion: unable to parse error body, $e")
            null
        }
    }

    // TODO, replace with Hilt
    private fun getApiService(): OpenAIService? {
        if (apiService == null) {
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
            apiService = retrofit.create(OpenAIService::class.java)
        }

        return apiService
    }

    companion object {
        private const val TAG = "OpenAIRepository"
    }
}