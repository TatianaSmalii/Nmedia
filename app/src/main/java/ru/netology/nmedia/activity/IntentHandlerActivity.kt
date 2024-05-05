package ru.netology.nmedia.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.ActivityIntentHandlerBinding

class IntentHandlerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityIntentHandlerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent?.let {
            if (it.action != Intent.ACTION_SEND) {
                return@let
            }
            val text = it.getStringExtra(Intent.EXTRA_TEXT)
            if (text.isNullOrBlank()) {//проверяем что текст не пустой, если не пустой тогла показываем
                Snackbar.make(
                    binding.root,
                    getString(R.string.error_empty_content),
                    Snackbar.LENGTH_SHORT
                ) //Snackbar - пока пользователь не нахмет на кнопку
                    .setAction(android.R.string.ok) {
                        finish()
                    }
                    .show()
                return
            }
        }
    }
}