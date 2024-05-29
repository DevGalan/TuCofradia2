package com.devgalan.tucofradia2.ui.profile

import android.app.Dialog
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.devgalan.tucofradia2.R
import com.devgalan.tucofradia2.databinding.FragmentProfileBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private val profileViewModel by viewModels<ProfileViewModel>()

    private lateinit var binding: FragmentProfileBinding

    private val pickMedia =
        registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            if (uri != null) {
                profileViewModel.uploadProfileImage(binding.ivProfile.context, uri)
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        checkIfUserIsLogged()

        binding = FragmentProfileBinding.inflate(inflater, container, false)

        suscribeObservers()

        initListeners()

        initUI()

        return binding.root
    }

    private fun checkIfUserIsLogged() {
        if (!profileViewModel.userIsLogged()) {
            findNavController().navigate(R.id.action_profileFragment_to_signinFragment)
        }
    }

    private fun suscribeObservers() {
        profileViewModel.onFinished.observe(viewLifecycleOwner) {
            if (it) {
                initUI()
            }
        }
    }

    private fun initListeners() {
        binding.btnLogout.setOnClickListener {
            profileViewModel.logout()
            findNavController().navigate(R.id.action_profileFragment_to_signinFragment)
        }

        binding.btnEditProfile.setOnClickListener() {
            showDialog()
        }

        binding.ivProfile.setOnClickListener {
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }
    }

    private fun showDialog() {
        val dialog = Dialog(binding.ivProfile.context)
        dialog.setContentView(R.layout.dialog_edit_profile)

        val btnSave = dialog.findViewById<Button>(R.id.btnSave)
        val etName = dialog.findViewById<EditText>(R.id.etName)
        val etMessage = dialog.findViewById<EditText>(R.id.etMessage)
        val fabCancel = dialog.findViewById<FloatingActionButton>(R.id.fabCancel)

        btnSave.setOnClickListener {
            profileViewModel.updateProfile(etName.text.toString(), etMessage.text.toString())
            dialog.dismiss()
        }

        fabCancel.setOnClickListener {
            dialog.dismiss()
        }

        val user = profileViewModel.getUser()
        val username = user?.username ?: ""
        val profileMessage = user?.profileMessage ?: ""

        etName.hint = "Nombre de usuario"
        etMessage.hint = "Mensaje de perfil"

        if (username.isNotEmpty()) {
            etName.setText(username)
        }
        if (profileMessage.isNotEmpty()) {
            etMessage.setText(profileMessage)
        }

        dialog.show()
    }

    private fun initUI() {
        binding.tvUsername.text = profileViewModel.getUser()?.username
        binding.tvEmail.text = profileViewModel.getUser()?.email
        binding.tvProfileMessage.text = profileViewModel.getUser()?.profileMessage
        if (profileViewModel.getUser()?.profileMessage.isNullOrEmpty()) {
            binding.tvProfileMessage.text = "No hay mensaje de perfil"
        }
        if (profileViewModel.getUser()?.profilePicturePath.isNullOrEmpty()) {
            binding.ivProfile.setImageResource(R.drawable.ic_profile)
        } else {
            Picasso.get().load(profileViewModel.getUser()?.profilePicturePath).centerCrop().fit().into(binding.ivProfile)
//            val builder = Picasso.Builder(binding.ivProfile.context)
//            builder.listener(fun(picasso: Picasso, uri: Uri, exception: Exception) {
//                exception.printStackTrace()
//            })
//            builder.build().load("https://i.blogs.es/d559d0/jlacort_happy_elephant_running_across_the_desert._4k_26e6e27c-dbd6-4b14-b8ba-ac0841f2c25f/1366_2000.jpeg").into(binding.ivProfile)
            println(profileViewModel.getUser()?.profilePicturePath)
        }
    }
}