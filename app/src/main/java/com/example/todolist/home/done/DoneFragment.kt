package com.example.todolist.home.done

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.databinding.FragmentDoneBinding
import com.example.todolist.home.recycler.TaskAdapter
import com.example.todolist.repository.TaskRepository

class DoneFragment : Fragment() {
    private var _binding: FragmentDoneBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: DoneViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDoneBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            DoneFactory(TaskRepository())
        )[DoneViewModel::class.java]

        initObservers()

        viewModel.fetchList()
    }

    private fun initObservers() {
        viewModel.getTasksResultLiveData().observe(viewLifecycleOwner) { result ->
            showTasks(result)
        }
    }

    private fun showTasks(tasks: List<String>) {
        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvDone.layoutManager = layoutManager
        val adapter = TaskAdapter(tasks)
        binding.rvDone.adapter = adapter
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {
        private const val NAME_OF_ITEM = "name"
        fun newInstance(name: String) = DoneFragment().apply {
            arguments = Bundle().apply {
                putString(NAME_OF_ITEM, name)
            }
        }
    }
}