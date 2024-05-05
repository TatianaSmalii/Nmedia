package ru.netology.nmedia.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.R
import androidx.activity.result.launch
import ru.netology.nmedia.adapter.OnInteractionListener
import ru.netology.nmedia.adapter.PostsAdapter
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.util.AndroidUtils
import ru.netology.nmedia.util.AndroidUtils.focusAndShowKeyboard
import ru.netology.nmedia.viewmodel.PostViewModel


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //println("onCreate $this")
        //работаем с Binding в gradle в нашем проекте
        val binding = ActivityMainBinding.inflate(layoutInflater) //получаем Binding и вызываем на нем метод inflate, layoutInflater - свойство активити
        setContentView(binding.root) //передаю метод set cont-view

        //val viewModel by viewModels <PostViewModel> ()
        val viewModel: PostViewModel by viewModels()

        val newPostContract = registerForActivityResult(NewPostContract) {result ->
            result ?: return@registerForActivityResult
            viewModel.changeContent(result)
//            viewModel.save()

        }
//        val newPostContract = registerForActivityResult(NewPostActivityContract()) { result ->
//            result ?: return@registerForActivityResult
//            viewModel.changeContentAndSave(result)
//        }
//
        val editPostContract = registerForActivityResult(EditPostActivityContract()) { result ->
            result?.let { viewModel.changeContentAndSave(result) }
                ?:viewModel.cancelEdit()
        //            result ?: return@registerForActivityResult
        //            viewModel.changeContentAndSave(result)
        }


        val adapter = PostsAdapter( object : OnInteractionListener{
            override fun onLike(post: Post) {
                viewModel.likeById(post.id)
                editPostContract.launch(post.content)
            }
            override fun onEdit(post: Post) {
                viewModel.edit(post)
                editPostContract.launch(post.content)
            }
            override fun onRemove(post: Post) {
                viewModel.removeById(post.id)
            }
            override fun onRepost(post: Post) {
                //viewModel.repost(post.id)
                val intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, post.content)
                    type = "text/plain"
                }
                val shareIntent = Intent.createChooser(intent, null)
                startActivity(shareIntent)
                viewModel.repost(post.id)

            }
            override fun openVideo(post: Post) {
                val webpage: Uri = Uri.parse(post.video)
                val intent = Intent(Intent.ACTION_VIEW, webpage)
                if (intent.resolveActivity(packageManager) != null){
                    startActivity(intent)
                }
            }
        }
        )
//        binding.root.setOnClickListener {//для обработчика
//            Log.d("root", "обработчик на root сработал")
//        }
        binding.list.adapter = adapter
        viewModel.data.observe(this) { posts ->
            val newPost = adapter.currentList.size < posts.size
            adapter.submitList(posts) {
                if (newPost) {
                    binding.list.smoothScrollToPosition(0)//автоматическая прокрутка на верх после добавления нового поста
                }
            }
        }
        viewModel.edited.observe(this) {post ->//текст редактируемого поста в поле ввода
            if (post.id != 0L){
                binding.content.setText(post.content)
                binding.contentText.setText(post.content)
                binding.group.visibility = View.VISIBLE
                binding.content.focusAndShowKeyboard()
            }
        }
        binding.closeEdit.setOnClickListener{
            binding.group.visibility = View.GONE
            binding.content.setText("")
            binding.content.clearFocus()
            AndroidUtils.hideKeyboard(it)
            viewModel.cancelEdit()
        }

        binding.save.setOnClickListener {
            val text = binding.content.text.toString()//получаем введенный текст поста
            if (text.isBlank()) {//если текст пустой, тогда нужно показать текст сообщение
                Toast.makeText(this,R.string.error_empty_content, Toast.LENGTH_LONG).show()
                return@setOnClickListener//выходим
            }
            viewModel.changeContent(text)//а иначе у модели вызываем changeContent, куда передаем обновленный text
            viewModel.save()

            binding.group.visibility = View.GONE
            binding.content.setText("")
            binding.content.clearFocus()//сброс мигания курсора после создания поста
            AndroidUtils.hideKeyboard(it)
        }
        binding.save.setOnClickListener{
            newPostContract.launch()
        }
    }
}