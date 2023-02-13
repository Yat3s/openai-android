package com.yat3s.openai.api.model

data class ImageGenerationApiResponse(
    val created: Long? = null,
    val data: List<ImageGenerateImageData>? = null,
    var error: TextCompletionError? = null,
)

data class ImageGenerateImageData(
    val url: String
)