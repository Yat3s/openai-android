package com.yat3s.openai.api.model

data class TextCompletionApiResponse(
    var id: String? = null,
    var created: String? = null,
    var choices: List<TextCompletionChoice>? = null,
    var error: TextCompletionError? = null,
)

data class TextCompletionChoice(
    val text: String?,
    val index: Int?,
    val finish_reason: String?,
)