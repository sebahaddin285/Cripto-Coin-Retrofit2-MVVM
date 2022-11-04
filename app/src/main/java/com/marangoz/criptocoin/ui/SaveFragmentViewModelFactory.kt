package com.marangoz.criptocoin.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.marangoz.criptocoin.room.CriptoDaoo

class SaveFragmentViewModelFactory (private val mDao: CriptoDaoo) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SaveFragmentViewModel(mDao) as T
    }

}