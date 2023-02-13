package com.yat3s.openai.api.model

import com.google.gson.annotations.SerializedName

internal data class ImageGenerationApiRequestBody internal constructor(
    val prompt: String,
    val size: String,
    @SerializedName("n") val generateCount: Int,
    @SerializedName("response_format") val responseFormat: String,
) : OpenAIRequestBody