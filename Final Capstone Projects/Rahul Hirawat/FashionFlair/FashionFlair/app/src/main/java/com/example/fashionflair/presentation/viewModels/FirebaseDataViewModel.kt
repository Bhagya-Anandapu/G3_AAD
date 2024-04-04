package com.example.fashionflair.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fashionflair.domain.repository.IFirebaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FirebaseDataViewModel @Inject constructor(
    private val repo: IFirebaseRepository
) : ViewModel() {


    fun getClothsData() {
        viewModelScope.launch {
            val result = repo.getDataFromDatabase()
//            when (result) {
////                ResponseHandler.Loading -> {
////                    //send loading UI
////                }
////
////                is ResponseHandler.Failure -> {
////                    //send failed UI
////                }
////
////                is ResponseHandler.Success -> {
////                    //send success screen UI
////                }
//            }
        }
    }

}