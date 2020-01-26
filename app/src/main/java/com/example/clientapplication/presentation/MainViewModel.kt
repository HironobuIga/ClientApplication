package com.example.clientapplication.presentation

import androidx.lifecycle.*
import com.example.entity.Repository
import com.example.repository.GithubRepository

class MainViewModel(
    private val repository: GithubRepository = GithubRepository()
): ViewModel() {
    val editText = MutableLiveData<String>("")

    val list = editText.switchMap {
        liveData {
            runCatching { repository.callRepository(it) }.fold(
                onSuccess = { list -> emit(list) },
                onFailure = { emit(emptyList<Repository>()) }
            )
        }
    }
}