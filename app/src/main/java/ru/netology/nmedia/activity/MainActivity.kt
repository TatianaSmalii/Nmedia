package ru.netology.nmedia.activity

//import ru.netology.nmedia.dto.countFormat
//import ru.netology.nmedia.dto.roundNoDecimal
//import ru.netology.nmedia.dto.roundWithDecimal
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.viewmodel.PostViewModel


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("onCreate $this")
        //работаем с Binding в gradle в нашем проекте
        val binding = ActivityMainBinding.inflate(layoutInflater) //получаем Binding и вызываем на нем метод inflate, layoutInflater - свойство активити
        setContentView(binding.root) //передаю метод sed cont-view

        val viewModel by viewModels <PostViewModel> ()
        viewModel.data.observe(this){post ->
            with(binding) {
                autor.text = post.author
                published.text = post.published
                content.text = post.content
                likeCount.text = reduceNumber(post.likes)
                repostCount.text = reduceNumber(post.repostCount)
                like.setImageResource(if (post.LikeByMe) R.drawable.baseline_favorite_24 else R.drawable.baseline_favorite_border_24)
                //likeCount.text = post.likes.toString()
                repost.setImageResource(if (post.repost) R.drawable.baseline_share_24 else R.drawable.baseline_share_black_24)
                //repostCount.text = post.repostCount.toString()
                //avatar.setImageResource(R.drawable.ic_launcher_foreground)

//                binding.root.setOnClickListener {//для обработчика
//                    Log.d("root", "обработчик на root сработал")
//                }
            }
        }
        binding.like.setOnClickListener {
            viewModel.like()
        }

        binding.repost.setOnClickListener{
            viewModel.repost()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        println("onDestroy $this")
    }
//    fun countFormat(likesCount: Int): String {
//        return when (likesCount) {
//            in 1000..1099 ->"${roundNoDecimal(likesCount.toDouble()/1_000.0)}K"
//            in 1100..9_999 ->"${roundWithDecimal(likesCount.toDouble()/1_000.0)}K"
//            in 10_000..999_999 ->"${roundNoDecimal(likesCount.toDouble()/1_000.0)}K"
//            in 1_000_000..1_099_999 ->"${roundNoDecimal(likesCount.toDouble()/1_000_000.0)}M"
//            in 1_100_000..Int.MAX_VALUE ->"${roundWithDecimal(likesCount.toDouble()/1_000_000.0)}M"
//
//            else-> likesCount.toString()
//        }
//    }
//    fun roundWithDecimal(number: Double): Double? {
//        val df = DecimalFormat("#.#")
//        df.roundingMode = RoundingMode.FLOOR
//        return df.format(number).toDouble()
//    }
//    fun roundNoDecimal(number: Double): Int? {
//        val df = DecimalFormat("#")
//        df.roundingMode = RoundingMode.FLOOR
//        return df.format(number).toInt()
//    }
fun reduceNumber(count: Int): String {
    val oneThousands = count / 1000
    val hundredThousands = (count % 1_000_000) / 100_000
    val oneHundreds = (count % 1000) / 100
    val oneMillion = (count / 1_000_000)

    when {
        count < 1000 -> return "$count"
        count in 1000..9_999 -> {

            return if (count % 1000 in 0..99) {
                "${oneThousands}k"
            } else {
                return "${oneThousands}.${oneHundreds}k"
            }
        }

        count in 10_000..999_999 -> {
            return "${oneThousands}k"
        }

        else -> {
            return if (count % 1_000_000 in 0..99_999) {
                "${oneMillion}M"
            } else {
                "${oneMillion}.${hundredThousands}M"
            }
        }
    }
}
}






