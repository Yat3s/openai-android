# OpenAI Android
The project for OpenAI official APIs

# Usages
```
implementation 'com.yat3s.openai:openai-android:0.0.2'
```

**Text completion:**
```
val response = OpenAIClient.Builder(BuildConfig.OPENAI_API_KEY).build().textCompletion(text)
```

**DALL-E 2:**
```
// To be implement
```


# Advance Settings
```
val response = OpenAIClient.Builder(BuildConfig.OPENAI_API_KEY)
    .maxTokens(1000)
    .temperature(0.6f)
    .model("you model")
    .build().textCompletion(text)
```

Please go [Open AI text completion parameters](https://platform.openai.com/docs/api-reference/completions/create) to check the definition of the parameters.

# How to get API KEY
Please go to [Open AI API KEY](https://platform.openai.com/account/api-keys) to create your API KEY.
![image](https://user-images.githubusercontent.com/14801837/218364643-bc5990e1-5122-49a9-a7dc-38c860a0c0a9.png)


