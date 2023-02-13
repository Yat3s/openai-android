package com.yat3s.openai.api.model

data class TextCompletionResponse(
    val text: String? = null,
    val errorMessage: String? = null,
    val apiResponseBody: TextCompletionApiResponse?,
)