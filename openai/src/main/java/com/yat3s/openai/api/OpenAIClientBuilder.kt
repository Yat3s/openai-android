package com.yat3s.openai.api

import com.yat3s.openai.api.model.ImageGenerationApiRequestBody
import com.yat3s.openai.api.model.ImageGenerationApiResponse
import com.yat3s.openai.api.model.OpenAIRequestBody
import com.yat3s.openai.api.model.TextCompletionApiRequestBody

abstract class OpenAIClientBuilder(val apiKey: String) {
    abstract fun toRequestBody(prompt: String): OpenAIRequestBody
}

class TextCompletionBuilder(apiKey: String) : OpenAIClientBuilder(apiKey) {
    private var model: String = DEFAULT_MODEL_TEXT_COMPLETION
    private var temperature: Float = DEFAULT_TEMPERATURE
    private var maxTokens: Int = DEFAULT_MAX_TOKENS

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

    override fun toRequestBody(prompt: String): OpenAIRequestBody {
        return TextCompletionApiRequestBody(
            prompt = prompt,
            model = model,
            temperature = temperature,
            maxTokens = maxTokens,
        )
    }

    companion object {
        private const val DEFAULT_MODEL_TEXT_COMPLETION = "text-davinci-003"
        private const val DEFAULT_TEMPERATURE = 1f
        private const val DEFAULT_MAX_TOKENS = 500
    }
}

class ImageGenerationBuilder(apiKey: String) : OpenAIClientBuilder(apiKey) {
    private var generateCount: Int = DEFAULT_IMAGE_COUNT
    private var imageSize: String = DEFAULT_IMAGE_SIZE
    private var imageResponseFormat: String = DEFAULT_IMAGE_RESPONSE_FORMAT

    fun generateCount(generateCount: Int) = apply {
        this.generateCount = generateCount
    }

    fun imageSize(imageSize: String) = apply {
        this.imageSize = imageSize
    }

    fun imageResponseFormat(imageResponseFormat: String) = apply {
        this.imageResponseFormat = imageResponseFormat
    }

    fun build(): OpenAIClient = OpenAIClient(this)

    override fun toRequestBody(prompt: String): OpenAIRequestBody {
        return ImageGenerationApiRequestBody(
            prompt = prompt,
            generateCount = generateCount,
            size = imageSize,
            responseFormat = imageResponseFormat,
        )
    }

    companion object {
        private const val DEFAULT_IMAGE_COUNT = 1
        private const val DEFAULT_IMAGE_SIZE = "1024x1024" // 256x256, 512x512
        private const val DEFAULT_IMAGE_RESPONSE_FORMAT = "url" // or b64_json
    }
}