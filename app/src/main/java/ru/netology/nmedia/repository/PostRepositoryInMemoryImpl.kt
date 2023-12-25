package ru.netology.nmedia.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.dto.Post

//создаем в начале интерфейс потом реализацию - выделяя интерфейс мы можем легко менять реализации в будущем
class PostRepositoryInMemoryImpl:PostRepository {

    private var post = Post(
        id = 1,
        author = "Нетология, Университет интернет-профессий будещего",
        published = "20 июня в 20:30",
        content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетенгу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растем сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остается с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия - помочь встать на путь роста и начать цепочку перемен - http://netolo.gy/fyb",
        LikeByMe = true,//чтобы по умолчанию стоял лайк
        likes = 1_000_000, //90 лайков сейчас
        repostCount = 1_000_000//кол-во репостов

    )
    private val data = MutableLiveData (post)
    override fun get(): LiveData<Post> =data

    override fun like() {
        post = post.copy(LikeByMe = !post.LikeByMe, likes = if (post.LikeByMe) post.likes -1 else post.likes + 1)//отметка лайка
        data.value = post
    }

    override fun repost() {
        post = post.copy(repost = !post.repost, repostCount = if (post.repost) post.repostCount -1 else post.repostCount +1)//отметка репоста
        data.value = post

//        post.repost = !post.repost //отметка репоста
//        post.repostCount += if (post.repost) 1 else -1
    }
}

