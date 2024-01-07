package ru.netology.nmedia.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.dto.Post

//создаем в начале интерфейс потом реализацию - выделяя интерфейс мы можем легко менять реализации в будущем
class PostRepositoryInMemoryImpl:PostRepository {
    private var nextId = 1L
    private var posts = listOf(
        Post(
            id = nextId++,
            author = "Нетология. Университет интернет-профессий будущего",
            content = "Освоение новой профессии — это не только открывающиеся возможности и перспективы, но и настоящий вызов самому себе. Приходится выходить из зоны комфорта и перестраивать привычный образ жизни: менять распорядок дня, искать время для занятий, быть готовым к возможным неудачам в начале пути. В блоге рассказали, как избежать стресса на курсах профпереподготовки → http://netolo.gy/fPD",
            published = "23 сентября в 10:12",
            LikeByMe = false,
//            likes = 1_000, //лайков сейчас
            repost = false,
//            repostCount = 1_000//кол-во репостов
        ),
        Post(
            id = nextId++,
            author = "Нетология. Университет интернет-профессий будущего",
            content = "Делиться впечатлениями о любимых фильмах легко, а что если рассказать так, чтобы все заскучали \uD83D\uDE34\n",
            published = "22 сентября в 10:14",
            LikeByMe = false,
//            likes = 1_000_000, //лайков сейчас
            repost = false,
//            repostCount = 1_000_000//кол-во репостов
        ),
        Post(
            id = nextId++,
            author = "Нетология. Университет интернет-профессий будущего",
            content = "Таймбоксинг — отличный способ навести порядок в своём календаре и разобраться с делами, которые долго откладывали на потом. Его главный принцип — на каждое дело заранее выделяется определённый отрезок времени. В это время вы работаете только над одной задачей, не переключаясь на другие. Собрали советы, которые помогут внедрить таймбоксинг \uD83D\uDC47\uD83C\uDFFB",
            published = "22 сентября в 10:12",
            LikeByMe = false,
//            likes = 1_000_000, //лайков сейчас
            repost = false,
//            repostCount = 1_000_000//кол-во репостов
        ),
        Post(
            id = nextId++,
            author = "Нетология. Университет интернет-профессий будущего",
            content = "\uD83D\uDE80 24 сентября стартует новый поток бесплатного курса «Диджитал-старт: первый шаг к востребованной профессии» — за две недели вы попробуете себя в разных профессиях и определите, что подходит именно вам → http://netolo.gy/fQ",
            published = "21 сентября в 10:12",
            LikeByMe = false,
//            likes = 1_000_000, //лайков сейчас
            repost = false,
//            repostCount = 1_000_000//кол-во репостов
        ),
        Post(
            id = nextId++,
            author = "Нетология. Университет интернет-профессий будущего",
            content = "Диджитал давно стал частью нашей жизни: мы общаемся в социальных сетях и мессенджерах, заказываем еду, такси и оплачиваем счета через приложения.",
            published = "20 сентября в 10:14",
            LikeByMe = false,
//            likes = 1_000_000, //лайков сейчас
            repost = false,
//            repostCount = 1_000_000//кол-во репостов
        ),
        Post(
            id = nextId++,
            author = "Нетология. Университет интернет-профессий будущего",
            content = "Большая афиша мероприятий осени: конференции, выставки и хакатоны для жителей Москвы, Ульяновска и Новосибирска \uD83D\uDE09",
            published = "19 сентября в 14:12",
            LikeByMe = false,
//            likes = 1_000_000, //лайков сейчас
            repost = false,
//            repostCount = 1_000_000//кол-во репостов
        ),
        Post(
            id = nextId++,
            author = "Нетология. Университет интернет-профессий будущего",
            content = "Языков программирования много, и выбрать какой-то один бывает нелегко. Собрали подборку статей, которая поможет вам начать, если вы остановили свой выбор на JavaScript.",
            published = "19 сентября в 10:24",
            LikeByMe = false,
//            likes = 1_000_000, //лайков сейчас
            repost = false,
//            repostCount = 1_000_000//кол-во репостов
        ),
        Post(
            id = nextId++,
            author = "Нетология, Университет интернет-профессий будещего",
            published = "18 сентября в 20:30",
            content = "Знаний хватит на всех: на следующей неделе разбираемся с разработкой мобильных приложений, учимся рассказывать истории и составлять PR-стратегию прямо на бесплатных занятиях",
            LikeByMe = false,//чтобы по умолчанию не стоял лайк
//            likes = 1_000_000, //лайков сейчас
            repost = false,
//            repostCount = 1_000_000//кол-во репостов
        ),
        Post(
            id = nextId++,
            author = "Нетология, Университет интернет-профессий будещего",
            published = "21 мая в 20:30",
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетенгу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растем сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остается с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия - помочь встать на путь роста и начать цепочку перемен - http://netolo.gy/fyb",
            LikeByMe = false,//чтобы по умолчанию не стоял лайк
//            likes = 1_000_000, //лайков сейчас
            repost = false,
//            repostCount = 1_000_000//кол-во репостов
        )
    )

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
}

