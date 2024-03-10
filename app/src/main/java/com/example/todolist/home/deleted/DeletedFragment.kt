package com.example.todolist.home.deleted

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.home.recycler.TaskAdapter
import com.example.todolist.repository.TaskRepository
import com.example.todolist.databinding.FragmentDeletedBinding
import com.example.todolist.data.datamodel.Task

class DeletedFragment : Fragment() {
    private var _binding: FragmentDeletedBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: DeletedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDeletedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            DeletedFactory(TaskRepository())
        )[DeletedViewModel::class.java]

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
        binding.rvDeleted.layoutManager = layoutManager
        val adapter = TaskAdapter(tasks)
        binding.rvDeleted.adapter = adapter
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {
        private const val NAME_OF_ITEM = "name"
        fun newInstance(name: String) = DeletedFragment().apply {
            arguments = Bundle().apply {
                putString(NAME_OF_ITEM, name)
            }
        }
    }
}