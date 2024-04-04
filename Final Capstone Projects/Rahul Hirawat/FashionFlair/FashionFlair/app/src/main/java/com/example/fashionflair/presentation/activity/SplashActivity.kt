package com.example.fashionflair.presentation.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.auth.api.identity.Identity
import com.example.fashionflair.databinding.ActivitySplashBinding
import com.example.fashionflair.presentation.service.GoogleAuthUiClient
import com.example.fashionflair.presentation.viewModels.SplashViewModel
import com.example.fashionflair.utils.gone
import com.example.fashionflair.utils.setLabel
import com.example.fashionflair.utils.visible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            oneTapClient = Identity.getSignInClient(applicationContext)
        )
    }
    private lateinit var binding: ActivitySplashBinding
    private val viewModel by viewModels<SplashViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observe()

        val launcher = registerForActivityResult(
            ActivityResultContracts.StartIntentSenderForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                lifecycleScope.launch {
                    val signInResult = googleAuthUiClient.signInWithIntent(
                        intent = result.data ?: return@launch
                    )
                    viewModel.onSignInResult(signInResult)
                }
            }
        }

        if (userAlreadyLoggedIn()) {
            binding.buttonPlaceholder.utilityBtn.gone()
            triggerOnLogin {
                val intent = Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        } else {
            binding.buttonPlaceholder.utilityBtn.visible()
            binding.buttonPlaceholder.utilityBtn.setLabel("Login with Google")
            binding.buttonPlaceholder.utilityBtn.setOnClickListener {
                lifecycleScope.launch {
                    val signInIntentSender = googleAuthUiClient.signIn()
                    launcher.launch(
                        IntentSenderRequest.Builder(
                            signInIntentSender ?: return@launch
                        ).build()
                    )
                }
            }
        }
    }

    private fun userAlreadyLoggedIn(): Boolean {
        return viewModel.getEmail().isNotEmpty()
    }

    private fun triggerOnLogin(action: () -> Unit) {
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                delay(3000)
            }
            action.invoke()
        }
    }

    private fun observe() {
        lifecycleScope.launchWhenStarted {
            viewModel.state.collectLatest { state ->
                if (state.isSignInSuccessful) {
                    Toast.makeText(
                        this@SplashActivity,
                        "Successfully Logging in",
                        Toast.LENGTH_SHORT
                    ).show()
                    val intent = Intent(this@SplashActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }
}