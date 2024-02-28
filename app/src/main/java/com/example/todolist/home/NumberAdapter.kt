package com.example.todolist.home

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.todolist.home.deleted.DeletedFragment
import com.example.todolist.home.done.DoneFragment
import com.example.todolist.home.HomeFragment
import com.example.todolist.home.inprogress.InProgressFragment

class NumberAdapter(fragment: HomeFragment): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 3
    override fun createFragment(position: Int): Fragment {
        val fragmentInProgress = InProgressFragment.newInstance("Screen = $position")
        val fragmentDone = DoneFragment.newInstance("Screen = $position")
        val fragmentDeleted = DeletedFragment.newInstance("Screen = $position")

        return when(position){
            0-> fragmentInProgress
            1-> fragmentDone
            2-> fragmentDeleted
            else -> {fragmentInProgress}
        }
    }
}