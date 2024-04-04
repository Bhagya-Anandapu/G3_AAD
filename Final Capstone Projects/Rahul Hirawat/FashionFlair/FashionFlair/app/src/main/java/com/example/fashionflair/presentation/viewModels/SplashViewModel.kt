package com.example.fashionflair.presentation.viewModels

import androidx.lifecycle.ViewModel
import com.example.fashionflair.domain.repository.ILocalRepository
import com.example.fashionflair.presentation.service.SignInResult
import com.example.fashionflair.presentation.service.SignInState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val repo: ILocalRepository
) : ViewModel() {

    private val _state = MutableStateFlow(SignInState())
    val state = _state.asStateFlow()

    fun onSignInResult(result: SignInResult) {
        setName(result.data?.username ?: "")
        setEmail(result.data?.userEmail ?: "")
        _state.update {
            it.copy(
                isSignInSuccessful = result.data != null,
                signInError = result.errorMessage
            )
        }
    }

    fun resetState() {
        _state.update { SignInState() }
    }

    fun getEmail(): String {
        return repo.getEmail()
    }

    fun setEmail(email: String) {
        return repo.setEmail(email)
    }

    fun setName(name: String) {
        return repo.setName(name)
    }
}