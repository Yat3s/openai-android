package com.yat3s.openai.api.model

data class TextCompletionApiResponse(
    val id: String?,
    val created: String?,
    val choices: List<TextCompletionChoice>?,
)

data class TextCompletionChoice(
    val text: String?,
    val index: Int,
    val finish_reason: String?,
)
