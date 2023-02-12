package com.yat3s.openai.api.model

import com.yat3s.openai.api.OpenAIClient

internal data class TextCompletionApiRequestBody internal constructor(
    val prompt: String,
    val model: String,
    val temperature: Float,
) {

    companion object {
        fun fromBuilder(
            builder: OpenAIClient.Builder,
            prompt: String
        ): TextCompletionApiRequestBody {
            return TextCompletionApiRequestBody(
                prompt = prompt,
                model = builder.model,
                temperature = builder.temperature,
            )
        }
    }
}