package ru.netology.nmedia.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContract
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.ActivityNewPostBinding

class NewPostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityNewPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.content.requestFocus()
        binding.save.setOnClickListener {
            val text = binding.content.text?.toString()
            if (text.isNullOrBlank()) {
                setResult(Activity.RESULT_CANCELED)
            } else {
                setResult(Activity.RESULT_OK, Intent().putExtra(Intent.EXTRA_TEXT, text))
            }
            finish()
        }
    }
}

object NewPostContract: ActivityResultContract<Unit, String?> () {
    override fun createIntent(context: Context, input: Unit)= Intent(context, NewPostActivity::class.java)

    override fun parseResult(resultCode: Int, intent: Intent?)= intent?.getStringExtra(Intent.EXTRA_TEXT)
}
