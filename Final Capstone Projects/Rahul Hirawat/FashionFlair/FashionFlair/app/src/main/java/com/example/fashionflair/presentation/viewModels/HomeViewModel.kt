package com.example.fashionflair.presentation.viewModels

import androidx.lifecycle.ViewModel
import com.example.fashionflair.domain.repository.ILocalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repo: ILocalRepository
) : ViewModel() {
    fun getName(): String?{
        return repo.getName()
    }
}