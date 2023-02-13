package com.yat3s.openai.api

import com.yat3s.openai.api.model.ImageGenerationApiRequestBody
import com.yat3s.openai.api.model.ImageGenerationResponse
import com.yat3s.openai.api.model.TextCompletionApiRequestBody
import com.yat3s.openai.api.model.TextCompletionResponse
import com.yat3s.openai.api.repository.OpenAIRepository

class OpenAIClient internal constructor(
    private val builder: OpenAIClientBuilder
) {
    suspend fun textCompletion(prompt: String): TextCompletionResponse? {
        val apiResponse = OpenAIRepository().textCompletion(
            builder.apiKey, builder.toRequestBody(prompt) as TextCompletionApiRequestBody
        ) ?: return null

        apiResponse.error?.let {
            return TextCompletionResponse(
                errorMessage = it.message,
                apiResponseBody = apiResponse,
            )
        }

        val apiResponseChoices = apiResponse.choices
        if (apiResponseChoices.isNullOrEmpty()) {
            return TextCompletionResponse(
                errorMessage = "Has response but no choice.",
                apiResponseBody = apiResponse,
            )
        }

        return TextCompletionResponse(
            text = apiResponseChoices[0].text,
            apiResponseBody = apiResponse,
        )
    }

    suspend fun imageGeneration(
        prompt: String
    ): ImageGenerationResponse? {
        val apiResponse = OpenAIRepository().imageGeneration(
            builder.apiKey, builder.toRequestBody(prompt) as ImageGenerationApiRequestBody
        ) ?: return null

        apiResponse.error?.let {
            return ImageGenerationResponse(
                errorMessage = it.message,
                apiResponseBody = apiResponse,
            )
        }

        return ImageGenerationResponse(
            urls = apiResponse.data?.map {
                it.url
            },
            apiResponseBody = apiResponse,
        )
    }
}
