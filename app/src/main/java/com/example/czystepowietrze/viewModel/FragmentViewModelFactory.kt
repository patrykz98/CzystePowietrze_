package com.example.retrofittest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.czystepowietrze.Repository.RetrofitRepository
import com.example.czystepowietrze.viewModel.FragmentViewModel

class FragmentViewModelFactory(private val retrofitRepository: RetrofitRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FragmentViewModel(retrofitRepository) as T
    }
}