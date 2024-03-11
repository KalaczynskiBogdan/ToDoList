package com.example.todolist.addtask

import android.Manifest.permission.POST_NOTIFICATIONS
import android.app.AlarmManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.todolist.MainActivity
import com.example.todolist.broadcast.AlarmReceiver
import com.example.todolist.databinding.FragmentAddTaskBinding
import com.example.todolist.data.datamodel.Task
import com.example.todolist.home.HomeFragment
import com.example.todolist.repository.TaskRepository
import java.util.Calendar

class AddTaskFragment : Fragment() {
    private var _binding: FragmentAddTaskBinding? = null
    private val binding get() = _binding!!

    private var selectedTimeInMillis: Long = 0

    private lateinit var viewModel: AddTaskViewModel

    private val requestPermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requestPermission.launch(POST_NOTIFICATIONS)

        viewModel =
            ViewModelProvider(this, AddTaskFactory(TaskRepository()))[AddTaskViewModel::class.java]

        selectTime()

        saveTask()

        changeTime()
    }
    private fun changeTime(){
        binding.ivChange.setOnClickListener {
            showTimePickerDialog()
        }
    }

    private fun selectTime() {
        binding.ivTime.setOnClickListener {
            showTimePickerDialog()
        }
    }

    private fun showTimePickerDialog() {
        val calendar: Calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(
            requireContext(),
            { _, hourOfDay, minuteOfDay ->
                calendar.apply {
                    set(Calendar.HOUR_OF_DAY, hourOfDay)
                    set(Calendar.MINUTE, minuteOfDay)
                }
                calendar.add(Calendar.MINUTE, -2)

                selectedTimeInMillis = calendar.timeInMillis

                val formatted = String.format("%02d:%02d", hourOfDay, minuteOfDay)
                binding.tvTime.text = formatted

                timeSelected()
            },
            hour,
            minute,
            true
        )
        timePickerDialog.show()
    }

    private fun startAlarm(timeInMillis: Long) {
        val alarmManager = requireContext().getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val intent = Intent(requireContext(), AlarmReceiver::class.java)
        intent.putExtra("alarm", "Time to do your task")

        val pendingIntent =
            PendingIntent.getBroadcast(requireContext(), 0, intent, PendingIntent.FLAG_IMMUTABLE)

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, timeInMillis, pendingIntent)
    }

    private fun timeSelected(){
        if (selectedTimeInMillis.toInt()!= 0){
            binding.tvTime.visibility = View.VISIBLE
            binding.ivTime.visibility = View.INVISIBLE
            binding.ivChange.visibility = View.VISIBLE
        }
    }

    private fun saveTask() {
        binding.btnSave.setOnClickListener {
            val tasks = viewModel.getInProgressList()

            val id = tasks.size
            val taskText = binding.TextInput.text.toString()
            val task = Task(id, taskText)

            viewModel.addNewTask(task)

            if (selectedTimeInMillis.toInt() != 0) {
                startAlarm(selectedTimeInMillis)
            }

            val fragment = HomeFragment()
            (activity as MainActivity).navigateToNextScreen(fragment)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}