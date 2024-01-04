package ru.netology.nmedia.dto

data class Post (
    val id: Long,
    val author: String,
    val published: String, //дата публикации
    val content: String,
    val LikeByMe: Boolean = false,//был ли проставлен лайк
    val likes: Int = 0,//кол-во лайков, значение по умолчанию 0
    val repost: Boolean =true,//был ли проставлен репост
    val repostCount: Int =0, //количество репостов
)
