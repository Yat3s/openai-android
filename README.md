# OpenAI Android
The project for OpenAI official APIs
- [x] Text Completion
- [x] Image Generation(DALL-E)
- [ ] Playground
- [ ] ChatGPT Web API(ChatGPT Plus)
- [ ] New Bing AI API

# Usages
```
implementation 'com.yat3s.openai:openai-android:0.0.4'
```

Or exclude OkHttp if you're using a version < `OkHttp3: 4.10.0`
```
implementation 'com.yat3s.openai:openai-android:0.0.4' {
   exclude group: "com.squareup.okhttp3"
}
```

## Text Completion
```
val response = TextCompletionBuilder(BuildConfig.OPENAI_API_KEY).build().textCompletion("Your prompt)
```

#### Advanced Configurations
```
val response = TextCompletionBuilder(OPENAI_API_KEY)
    .maxTokens(1000) // 1 - 4000, default 500
    .temperature(0.6f) // 0 - 2f, default 1f
    .model("you model") // default "text-davinci-003"
    .build().textCompletion(text)
```

Please check the definition on [Open AI text completion parameters](https://platform.openai.com/docs/api-reference/completions/create)

## Image Generation(DALL-E)
```
val response = ImageGenerationBuilder(BuildConfig.OPENAI_API_KEY).build().imageGeneration("Your prompt")
```
#### Advanced Configurations
```
val response = ImageGenerationBuilder(OPENAI_API_KEY)
    .generateCount(2) // The image count to generate, default 1
    .imageSize("512x512") // "256x256", "512x512", "1024x1024" are supported, default "1024x1024"
    .imageResponseFormat("url") // "url", "b64_json" are supported, default "url"
    .build()
    .imageGeneration("Your prompt")
```


# How to get API KEY
Please go to [Open AI API KEY](https://platform.openai.com/account/api-keys) to create your API keys.
![image](https://user-images.githubusercontent.com/14801837/218364643-bc5990e1-5122-49a9-a7dc-38c860a0c0a9.png)


