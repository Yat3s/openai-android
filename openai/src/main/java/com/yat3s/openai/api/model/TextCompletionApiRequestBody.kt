package com.yat3s.openai.api.model

import com.google.gson.annotations.SerializedName

internal data class TextCompletionApiRequestBody internal constructor(
    val prompt: String,
    val model: String,
    val temperature: Float,
    @SerializedName("max_tokens") val maxTokens: Int,
) : OpenAIRequestBody