package ru.netology.nmedia.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.R
import ru.netology.nmedia.adapter.PostsAdapter
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.databinding.CardPostBinding
import ru.netology.nmedia.viewmodel.PostViewModel


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //println("onCreate $this")
        //работаем с Binding в gradle в нашем проекте
        val binding =
            ActivityMainBinding.inflate(layoutInflater) //получаем Binding и вызываем на нем метод inflate, layoutInflater - свойство активити
        setContentView(binding.root) //передаю метод sed cont-view

        //val viewModel by viewModels <PostViewModel> ()
        val viewModel: PostViewModel by viewModels()
        val adapter = PostsAdapter(
            { viewModel.likeById(it.id) },
            { viewModel.repost(it.id) }
        )
        binding.list.adapter = adapter
        viewModel.data.observe(this) { posts ->
            adapter.submitList(posts)

//            binding.container.removeAllViews()//очистка постов перед заполнением и далее их добавляем
//            posts.map { post ->
//                CardPostBinding.inflate(layoutInflater, binding.container,true).apply{
//                    autor.text = post.author
//                    published.text = post.published
//                    content.text = post.content
//                    likeCount.text = reduceNumber(post.likes)
//                    like.setImageResource(
//                        if (post.LikeByMe) R.drawable.baseline_favorite_24 else R.drawable.baseline_favorite_border_24
//                    )
//                    like.setOnClickListener {
//                        viewModel.likeById(post.id)
//                    }
//                    repostCount.text = reduceNumber(post.repostCount)
//                    repost.setImageResource(
//                        if (post.repost) R.drawable.baseline_share_24 else R.drawable.baseline_share_black_24
//                    )
//                    repost.setOnClickListener{
//                        viewModel.repost(post.id)
//                    }
//                }.root
//            }
//            with(binding) {
//                likeCount.text = reduceNumber(post.likes)
//                repostCount.text = reduceNumber(post.repostCount)
//                like.setImageResource(if (post.LikeByMe) R.drawable.baseline_favorite_24 else R.drawable.baseline_favorite_border_24)
//                repost.setImageResource(if (post.repost) R.drawable.baseline_share_24 else R.drawable.baseline_share_black_24)
            //avatar.setImageResource(R.drawable.ic_launcher_foreground)

//                binding.root.setOnClickListener {//для обработчика
//                    Log.d("root", "обработчик на root сработал")
//                }
//            }
        }
    }
}
//    override fun onDestroy() {
//        super.onDestroy()
//        println("onDestroy $this")
//    }
//}







