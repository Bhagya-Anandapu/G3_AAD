package com.example.fashionflair.presentation.viewModels

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.example.fashionflair.domain.repository.ILocalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repo: ILocalRepository
) : ViewModel() {

    fun getEmail(): String {
        return repo.getEmail()
    }

    fun setProfile(uri: Uri) {
        repo.setProfilePicture(uri)
    }

    fun getProfile(): Uri? {
        return repo.getProfilePicture()
    }

    fun clearUserData() {
        repo.clearData()
    }
}