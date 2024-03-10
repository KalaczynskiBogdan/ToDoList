package com.example.todolist.home.inprogress

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.databinding.FragmentInProgressBinding
import com.example.todolist.data.datamodel.Task
import com.example.todolist.home.recycler.TaskAdapter
import com.example.todolist.repository.TaskRepository

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

        viewModel.fetchList()
    }

    private fun initObservers() {
        viewModel.getTasksResultLiveData().observe(viewLifecycleOwner) { result ->
            showTasks(result)
        }
    }

    private fun showTasks(tasks: ArrayList<Task>) {
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