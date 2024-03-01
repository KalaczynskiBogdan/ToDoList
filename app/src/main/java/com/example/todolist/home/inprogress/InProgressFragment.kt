package com.example.todolist.home.inprogress

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.databinding.FragmentInProgressBinding
import com.example.todolist.home.recycler.TaskAdapter
import com.example.todolist.repository.TaskRepository
import kotlinx.coroutines.launch

class InProgressFragment : Fragment() {
    private var _binding: FragmentInProgressBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: InProgressViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInProgressBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            InProgressFactory(TaskRepository())
        )[InProgressViewModel::class.java]

        initObservers()

        viewModel.getList()
    }

    private fun initObservers() {
        lifecycleScope.launch{
            viewModel.getTasksFlow().collect{
                showTasks(it)
            }
        }
    }

    private fun showTasks(tasks: List<String>) {
        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvInProgress.layoutManager = layoutManager
        val adapter = TaskAdapter(tasks)
        binding.rvInProgress.adapter = adapter
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {
        private const val NAME_OF_ITEM = "name"
        fun newInstance(name: String) = InProgressFragment().apply {
            arguments = Bundle().apply {
                putString(NAME_OF_ITEM, name)
            }
        }
    }
}