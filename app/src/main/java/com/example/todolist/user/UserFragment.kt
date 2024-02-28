package com.example.todolist.user

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import com.example.todolist.MainActivity
import com.example.todolist.databinding.FragmentUserBinding
import com.example.todolist.home.HomeFragment

class UserFragment : Fragment() {
    private var isInputEnabled = true

    private val galleryLauncher =
        registerForActivityResult(ActivityResultContracts.GetContent()) { galleryUri ->
            binding.imageUser.setImageURI(galleryUri)
        }

    private var _binding: FragmentUserBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClickListeners()
        binding.btnBack.setOnClickListener {
            val fragment = HomeFragment()
            (activity as MainActivity).navigateToNextScreen(fragment)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
    private fun initClickListeners(){
        binding.imageUser.setOnClickListener {
            galleryLauncher.launch("image/*")
        }
        binding.btnSave.setOnClickListener {
            binding.tilName.apply {
                isClickable = isInputEnabled
                isEnabled = isInputEnabled
            }
            binding.imageUser.apply {
                isFocusable = isInputEnabled
                isClickable = isInputEnabled
                isEnabled = isInputEnabled
            }
            binding.btnSave.text = if (isInputEnabled) {
                "save"
            } else {
                "change"
            }
            isInputEnabled = !isInputEnabled
        }
    }
}