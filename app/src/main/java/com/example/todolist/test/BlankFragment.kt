package com.example.todolist.test

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.todolist.R
import com.example.todolist.databinding.FragmentBlankBinding
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class BlankFragment : Fragment() {
    private var _binding: FragmentBlankBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: TestViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBlankBinding.inflate(inflater, container, false)
        viewModel =
            ViewModelProvider(this, TestFactory(TestRepository()))[TestViewModel::class.java]
        init()

        binding.button.setOnClickListener {
            viewModel.fetchNames()
        }

//        runBlocking {
//            val result = async {
//                doWork()
//            }
//            val res = result.await()
//        }
        return binding.root
    }

    private fun init() {
        viewModel.getNamesResultLiveData().observe(viewLifecycleOwner) { result ->
            binding.tvSomeText.text = result.toString()
        }
    }

    private suspend fun doWork(): String {
        delay(5000)
        return "Done"
    }
}