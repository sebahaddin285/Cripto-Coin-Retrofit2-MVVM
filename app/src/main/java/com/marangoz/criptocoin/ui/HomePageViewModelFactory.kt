package com.marangoz.criptocoin.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.marangoz.criptocoin.repository.RetrofitRepo

class HomePageViewModelFactory (private val repository: RetrofitRepo) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomePageViewModel(repository) as T
    }

}