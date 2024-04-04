package com.example.fashionflair.presentation.fragment

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.android.gms.auth.api.identity.Identity
import com.example.fashionflair.R
import com.example.fashionflair.databinding.FragmentProfileBinding
import com.example.fashionflair.presentation.activity.SplashActivity
import com.example.fashionflair.presentation.service.GoogleAuthUiClient
import com.example.fashionflair.presentation.viewModels.ProfileViewModel
import com.example.fashionflair.utils.gone
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private val viewModel by viewModels<ProfileViewModel>()
    private lateinit var galleryPickerLauncher: ActivityResultLauncher<String>
    private val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            oneTapClient = Identity.getSignInClient(requireContext())
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (viewModel.getEmail() != "null") {
            binding.profileEmail.text = viewModel.getEmail()
        } else {
            binding.profileEmail.gone()
        }

        setProfilePicture()

        galleryPickerLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) {
            if (it != null) {
                binding.profilePicture.setImageURI(it)
                viewModel.setProfile(it)
            }
        }

        binding.viewCartBtn.setOnClickListener {
            findNavController().navigate(R.id.action_profile_to_cart)
        }

        binding.editProfilePicture.setOnClickListener {
            galleryPickerLauncher.launch("image/*")
        }

        binding.helpBtn.setOnClickListener {
            Toast.makeText(
                requireContext(),
                "Redirecting to Customer Support",
                Toast.LENGTH_SHORT
            ).show()
        }

        binding.termsBtn.setOnClickListener {
            Toast.makeText(
                requireContext(),
                "Redirecting to terms and condition",
                Toast.LENGTH_SHORT
            ).show()
        }

        binding.privacyBtn.setOnClickListener {
            Toast.makeText(requireContext(), "Redirecting to Privacy Policy", Toast.LENGTH_SHORT)
                .show()
        }

        binding.logoutBtn.setOnClickListener {
            lifecycleScope.launch {
                googleAuthUiClient.signOut()
            }
            viewModel.clearUserData()
            val intent = Intent(requireContext(), SplashActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
    }

    private fun setProfilePicture() {
        val profileUri = viewModel.getProfile()
        if (profileUri != null) {
            if (requireContext().contentResolver.persistedUriPermissions.any { it.uri == profileUri }) {
                Glide.with(requireContext()).load(profileUri).into(binding.profilePicture)
            } else {
                Glide.with(requireContext()).load(R.drawable.ic_profile_placeholder)
                    .into(binding.profilePicture)
            }
        }
    }

    private fun getBitmapFromUri(uri: Uri): Bitmap? {
        var bm: Bitmap? = null
        lifecycleScope.launch {
            val contentResolver = activity?.applicationContext?.contentResolver
            val parcelFileDescriptor = contentResolver?.openFileDescriptor(uri, "r")
            val fileDescriptor = parcelFileDescriptor?.fileDescriptor
            bm = BitmapFactory.decodeFileDescriptor(fileDescriptor)
            parcelFileDescriptor?.close()
        }
        return bm
    }
}