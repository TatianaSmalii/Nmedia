package ru.netology.nmedia.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.netology.nmedia.dto.Post

//создаем в начале интерфейс потом реализацию - выделяя интерфейс мы можем легко менять реализации в будущем
class PostRepositoryFileImpl(private val context: Context):PostRepository {
    private val gson = Gson()
    private val type = TypeToken.getParameterized(List::class.java, Post::class.java).type
    private val filename = "posts.json"

    private var nextId = 1L

    private var posts = emptyList<Post>()
        set(value) {
            field = value
            sync()
        }
    init {
        val file = context.filesDir.resolve(filename)
        if (file.exists()) {
            context.openFileInput(filename).bufferedReader().use {
                posts = gson.fromJson(it, type)
                if (posts.isEmpty()){
                    nextId = 1L
                } else {
                    nextId = posts.maxOf { it.id } + 1
                }
                data.value = posts
            }
        }
    }

    private val data = MutableLiveData (posts)
    override fun getAll(): LiveData <List<Post>> = data
    override fun likeById(id: Long) {
        posts = posts.map {
            if (it.id != id) it else it.copy(
                LikeByMe = !it.LikeByMe,
                likes = if (it.LikeByMe) it.likes - 1  else it.likes +1 )
            //post = post.copy(LikeByMe = !post.LikeByMe, likes = if (post.LikeByMe) post.likes -1 else post.likes + 1)//отметка лайка
        }
        data.value = posts
    }

    override fun repost(id: Long) {
        posts = posts.map {
            if (it.id != id) it else it.copy(repost = !it.repost, repostCount = if (it.repost) it.repostCount -1 else it.repostCount +1) //repost = !it.repost, repostCount = if (it.repost) it.repostCount -1 else it.repostCount +1
            //post = post.copy(repost = !post.repost, repostCount = if (post.repost) post.repostCount -1 else post.repostCount +1)//отметка репоста
        }
        data.value = posts
//      post.repost = !post.repost //отметка репоста
//      post.repostCount += if (post.repost) 1 else -1
    }

    override fun removeById(id: Long) {
        //обновляем список постов через фильтр и отображ. только те посты id кот. не равны удаляемому
        posts = posts.filter { it.id != id }
        data.value = posts //отображаем откорректированный список
    }
    override fun save (post: Post){
        posts = if (post.id == 0L) {//добавление поста если ид поста равен нулю
            listOf(post.copy(
                id = nextId++,
                author = "Me",
                published = "Now",

                ))+posts//прибавляем старый список постов
        } else {
            posts.map { if (it.id != post.id) it else it.copy(content = post.content) }//обновляем список постов через map
        }
        data.value = posts
    }
    private fun sync() {
        context.openFileOutput(filename, Context.MODE_PRIVATE).bufferedWriter().use {
            it.write(gson.toJson(posts))
        }
    }
}