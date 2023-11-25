package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.dto.countFormat
import java.math.RoundingMode
import java.text.DecimalFormat


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //работаем с Binding в gradle в нашем проекте
        val binding = ActivityMainBinding.inflate(layoutInflater) //получаем Binding и вызываем на нем метод inflate, layoutInflater - свойство активити
        setContentView(binding.root) //передаю метод sed cont-view

        //R -это класс для доступа к ресурсам, Image_like- название картинки в activity main
//        findViewById<ImageButton>(R.id.Image_like).setOnClickListener {//view это ImageButton на которой было осущ. нажатие
//            (it as ImageButton).setImageResource(R.drawable.baseline_favorite_24)//передаем ссылку на нашу картинку
//        }
        //Создаю объект класса
        val post = Post(
            id = 1,
            author = "Нетология, Университет интернет-профессий будещего",
            published = "20 июня в 20:30",
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетенгу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растем сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остается с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия - помочь встать на путь роста и начать цепочку перемен - http://netolo.gy/fyb",
            LikeByMe = true,//чтобы по умолчанию стоял лайк
            likes = 1_000_000, //90 лайков сейчас
            repostCount = 1_000_000//кол-во репостов

        )
//        //Далее вызываем метод set.OnClickListener
//        binding.Avatar.setOnClickListener {
//            binding.Avatar.setImageResource(R.drawable.ic_launcher_foreground)//передаем ссылку на нашу картинку
//        }

        //заполняем данными нашу разметку
        with(binding) {
            Autor.text = post.author
            Published.text = post.published
            Content.text = post.content
            likeCount.text = countFormat(post.likes)
            RepostCount.text = countFormat(post.repostCount)
            Avatar.setImageResource(R.drawable.ic_launcher_foreground)

            if (post.LikeByMe) {//ставим сердечко
                like.setImageResource(R.drawable.baseline_favorite_24)
            }
            //
            if (post.repost) {//если проставлен репост
                Repost.setImageResource(R.drawable.baseline_share_black_24)//то репост поменяет цвет
            }
            Avatar.setOnClickListener{
                Avatar.setImageResource(R.drawable.ic_launcher_foreground)
            }
            like.setOnClickListener {
                post.LikeByMe = !post.LikeByMe //отметка лайка
                post.likes += if (post.LikeByMe) 1 else -1 //кол-во лайков
                like.setImageResource(if (post.LikeByMe) R.drawable.baseline_favorite_24 else R.drawable.baseline_favorite_border_24)
                likeCount.text = countFormat(post.likes)
            }

            Repost.setOnClickListener{
                post.repost = !post.repost //отметка репоста
                post.repostCount += if (post.repost) 1 else -1
                Repost.setImageResource(if (post.repost) R.drawable.baseline_share_24 else R.drawable.baseline_share_black_24)
                RepostCount.text = countFormat(post.repostCount)
                //RepostCount.text = post.repostCount.toString()

                //post.repost = post.repost //отметка репоста
                //post.repostCount += if (post.repost) 1 else -1 //кол-во репостов
                    //if (post.repostCount >= 1000){
                    //post.repostCount = post.repostCount/1000//+"K"//+ post.textForCountShare
                    //    //toString(RepostCount) + post.textForCountShare
                    //}

            }
        }
    }
}






