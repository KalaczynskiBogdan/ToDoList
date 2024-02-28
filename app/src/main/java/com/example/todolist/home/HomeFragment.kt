package com.example.todolist.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.todolist.MainActivity
import com.example.todolist.R
import com.example.todolist.user.UserFragment
import com.example.todolist.databinding.FragmentListBinding
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment() {
    private lateinit var adapter: NumberAdapter

    private val tabNames = listOf(
        "in progress",
        "done",
        "deleted"
    )

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showList()
        onBottomNavigationClick()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun showList() {
        adapter = NumberAdapter(this)
        binding.viewPager.adapter = adapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = tabNames[position]
        }.attach()
    }

    private fun onBottomNavigationClick() {
        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.user -> {
                    val fragment = UserFragment()
                    (activity as MainActivity).navigateToNextScreen(fragment)
                    true
                }
                else -> false
            }
        }
    }
}