package com.yat3s.openai.demo

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.marginEnd
import androidx.core.view.updatePaddingRelative
import com.squareup.picasso.Picasso
import com.yat3s.openai.api.ImageGenerationBuilder
import com.yat3s.openai.api.OpenAIClient
import com.yat3s.openai.api.TextCompletionBuilder
import com.yat3s.openai.demo.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textCompletion.setOnClickListener {
            GlobalScope.launch {
                val response = TextCompletionBuilder(BuildConfig.OPENAI_API_KEY)
                    .build()
                    .textCompletion(binding.input.text.toString())
                withContext(Dispatchers.Main) {
                    binding.textCompletionOutput.text = "Output: ${response?.text}"
                }
            }
        }

        binding.imageGeneration.setOnClickListener {
            GlobalScope.launch {
                val response = ImageGenerationBuilder(BuildConfig.OPENAI_API_KEY)
                    .generateCount(2)
                    .build()
                    .imageGeneration(binding.input.text.toString())

                withContext(Dispatchers.Main) {
                    val urls = response?.urls
                    if (!urls.isNullOrEmpty()) {
                        urls.forEach {
                            val imageView = ImageView(this@MainActivity)
                            binding.imageGenerationContainer.addView(imageView)
                            imageView.layoutParams.apply {
                                width = 500
                                height = 500
                            }
                            imageView.updatePaddingRelative(end = 12)

                            Picasso.get().load(it).fit()
                                .centerInside()
                                .into(imageView)
                        }
                    }
                }
            }
        }
    }
}