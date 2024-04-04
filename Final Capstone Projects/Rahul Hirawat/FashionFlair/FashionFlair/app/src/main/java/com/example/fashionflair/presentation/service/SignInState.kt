package com.example.fashionflair.presentation.service

data class SignInState(
    val isSignInSuccessful: Boolean = false,
    val signInError: String? = null
)