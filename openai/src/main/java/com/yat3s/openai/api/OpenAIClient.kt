package com.yat3s.openai.api

import com.yat3s.openai.api.model.TextCompletionApiRequestBody
import com.yat3s.openai.api.model.TextCompletionResponse
import com.yat3s.openai.api.repository.OpenAIRepository

class OpenAIClient internal constructor(
    private val builder: Builder
) {
    class Builder(val apiKey: String) {
        internal var model: String = Config.DEFAULT_MODEL_TEXT_COMPLETION
        internal var temperature: Float = Config.DEFAULT_TEMPERATURE
        internal var maxTokens: Int = Config.DEFAULT_MAX_TOKENS

        fun model(model: String) = apply {
            this.model = model
        }

        fun temperature(temperature: Float) = apply {
            this.temperature = temperature
        }

        fun maxTokens(maxTokens: Int) = apply {
            this.maxTokens = maxTokens
        }

        fun build(): OpenAIClient = OpenAIClient(this)
    }

    suspend fun textCompletion(prompt: String): TextCompletionResponse? {
        val apiResponse = OpenAIRepository.textCompletion(
            builder.apiKey,
            TextCompletionApiRequestBody.fromBuilder(builder, prompt)
        )

        apiResponse?.error?.let {
            return TextCompletionResponse(
                text = apiResponse.error?.message,
                apiResponseBody = apiResponse,
            )
        }

        val apiResponseChoices = apiResponse?.choices
        if (apiResponseChoices.isNullOrEmpty()) {
            return null
        }

        return TextCompletionResponse(
            text = apiResponseChoices[0].text,
            apiResponseBody = apiResponse,
        )
    }
}