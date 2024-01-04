package ru.netology.nmedia.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.CardPostBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.dto.Number

//создание своего собственно типа для передачи его в параметры
typealias Listener = (Post)-> Unit
class PostsAdapter(
    private val OnLikeListener: Listener,
    private val OnRepostListener: Listener
    ): ListAdapter<Post, PostViewHolder>(PostDiffCallback) {

//    //Нужно объявить перед функциями коллекцию из элементов
//    var list : List <Post> = emptyList()
//    //переопределяем метод set метод установки значений
//    set(value) {
//        field = value//сохраняю значение
//        notifyDataSetChanged()//уведомление о том что необходимо данные переадресовать
//    }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {//отвечает за создание разметки
            val binding = CardPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return PostViewHolder(binding, OnLikeListener, OnRepostListener)//возвращаем вывод с этой разметкой
        }

        override fun onBindViewHolder(holder: PostViewHolder, position: Int) {//функ-я которая связывает текущий пост PostViewHolder с элементом коллекции, с указанной позицией
    //        val post = list [position]
            val post = getItem(position)
            holder.bind (post)
        }

//      override fun getItemCount() = list.size //возращает размер этого списка(сколько постов необходимо на экране отображать
}
class PostViewHolder (private val binding: CardPostBinding,
                      private val OnLikeListener: Listener,
                      private val OnRepostListener: Listener
                      ): RecyclerView.ViewHolder (binding.root){

    fun bind(post: Post) {
        binding.apply{//делаем все на нашей разметке
            autor.text = post.author
            published.text = post.published
            content.text = post.content
            likeCount.text = Number.reduceNumber (post.likes)
//          likeCount.text = post.likes.toString()
            repostCount.text = Number.reduceNumber (post.repostCount)
//          repostCount.text = post.repost.toString()
            like.setImageResource(
                if (post.LikeByMe) R.drawable.baseline_favorite_24 else R.drawable.baseline_favorite_border_24
            )
            like.setOnClickListener {
                OnLikeListener (post)
//                viewModel.likeById(post.id)
            }

            repost.setImageResource(
                if (post.repost) R.drawable.baseline_share_black_24 else R.drawable.baseline_share_24
            )
            repost.setOnClickListener{
                OnRepostListener (post)
//                viewModel.repost(post.id)
            }
        }
    }

}

object PostDiffCallback : DiffUtil.ItemCallback<Post>(){
    override fun areItemsTheSame(oldItem: Post, newItem: Post)= oldItem.id == newItem.id //совпадают ли у нас элементы
    override fun areContentsTheSame(oldItem: Post, newItem: Post)=oldItem == newItem //проверка на элементы
}