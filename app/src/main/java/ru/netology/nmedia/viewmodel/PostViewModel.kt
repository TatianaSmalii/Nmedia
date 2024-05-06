package ru.netology.nmedia.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.repository.PostRepository
//import ru.netology.nmedia.repository.PostRepositoryInMemoryImpl
import ru.netology.nmedia.repository.PostRepositoryFileImpl

private val empty = Post (
    id =0,
    content = "",
    author = "",
    published = "",
    LikeByMe = false,
    repost = false,
)
class PostViewModel(application: Application): AndroidViewModel (application) { //class PostViewModel: ViewModel () {

    //создаем репозиторий
    //private val repository:PostRepository = PostRepositoryInMemoryImpl()
    private val repository: PostRepository = PostRepositoryFileImpl(application)
    val data = repository.getAll()
    val edited = MutableLiveData(empty)
    fun likeById(id: Long) = repository.likeById(id)
    fun repost(id: Long) = repository.repost(id)
    fun removeById (id: Long) = repository.removeById(id)
    fun save (){
        edited.value?.let {
            repository.save(it)
        }
        edited.value= empty
    }
    fun edit (post: Post){
        edited.value = post
    }
    fun changeContent (content: String) {//изменение содержимого
        edited.value?.let { //post ->
            val text = content.trim()
            if (it.content != text){ //if (text != post.content){
            edited.value = it.copy (content = text)//post.copy(content = text)
            }
        }
    }
    fun cancelEdit() {
        edited.value = empty
    }
    fun changeContentAndSave (content: String) {
        val text = content.trim()
        if (edited.value?.content == text){
            edited.value = empty
            return
        }
        edited.value?.let {
            repository.save(it.copy(content = text))
        }
        edited.value = empty
    }
}