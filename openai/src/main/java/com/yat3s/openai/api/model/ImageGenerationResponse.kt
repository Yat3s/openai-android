package com.yat3s.openai.api.model

data class ImageGenerationResponse(
    val urls: List<String>? = null,
    val errorMessage: String? = null,
    val apiResponseBody: ImageGenerationApiResponse?,
)