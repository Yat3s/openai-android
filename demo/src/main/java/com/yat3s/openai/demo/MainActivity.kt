package com.yat3s.openai.demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.yat3s.openai.api.OpenAIClient
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

        binding.submit.setOnClickListener {
            ask(binding.input.text.toString())
        }
    }

    private fun ask(text: String) {
        GlobalScope.launch {
            val response = OpenAIClient.Builder(BuildConfig.OPENAI_API_KEY).build().textCompletion(text)
            withContext(Dispatchers.Main) {
                binding.textview.text = "Output: ${response?.text}"
            }
        }
    }
}