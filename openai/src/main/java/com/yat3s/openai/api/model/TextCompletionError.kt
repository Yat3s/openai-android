package com.yat3s.openai.api.model

data class TextCompletionError(
    val message: String?,
    var type: String? = null,
    var code: String? = null,
)