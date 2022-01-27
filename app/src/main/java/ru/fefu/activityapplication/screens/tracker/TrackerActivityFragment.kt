package ru.fefu.activityapplication.screens.tracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.fefu.activityapplication.App
import ru.fefu.activityapplication.models.DataOfActivity
import ru.fefu.activityapplication.R
import ru.fefu.activityapplication.adapters.AdapterActivityList
import ru.fefu.activityapplication.databinding.TrackerActivityFragmentBinding
import ru.fefu.activityapplication.enums.ActivityEnums
import ru.fefu.activityapplication.models.DateTimeData
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

class TrackerActivityFragment : Fragment(R.layout.tracker_activity_fragment) {
    private var _binding: TrackerActivityFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var items: MutableList<DataOfActivity>

    private val activities = mutableListOf<DataOfActivity>()
    private val data_activities = mutableListOf<Any>()

    private val map = mapOf(1 to "Январь", 2 to "Февраль", 3 to "Март",
        4 to "Апрель", 5 to "Май", 6 to "Июнь",
        7 to "Июль", 8 to "Август", 9 to "Сентябрь",
        10 to "Октябрь", 11 to "Ноябрь", 12 to "Декабрь")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = TrackerActivityFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }
    private val dataActivities = mutableListOf<Any>()
    private fun fillDate(activities: List<DataOfActivity>) {
        val cur = LocalDateTime.now()
        var date = DateTimeData("")
        for (activity in activities) {
            if (cur.year == activity.endDate.year &&
                cur.monthValue == activity.endDate.monthValue &&
                cur.dayOfMonth == activity.endDate.dayOfMonth) {
                if (date.date != "Сегодня") {
                    date = DateTimeData("Сегодня")
                    dataActivities.add(date)
                }
            }
            else {
                if (date.date != map.get(activity.endDate.monthValue) + ' ' + activity.endDate.year.toString()  + " года") {
                    date = DateTimeData(map.get(activity.endDate.monthValue) + ' '+activity.endDate.year.toString() + " года")
                    dataActivities.add(date)
                }
            }
            dataActivities.add(activity)
        }
    }
    private val adapter = AdapterActivityList(dataActivities)

    private fun changeFragment(position: Int) {
        if (position in dataActivities.indices) {
            val manager = activity?.supportFragmentManager?.findFragmentByTag(ActivityTab.tag)?.childFragmentManager
            manager?.beginTransaction()?.apply {
                manager.fragments.forEach(::hide)
                add (
                    R.id.tab_fragment_container,
                    MyInfo.newInstance(data_activities[position] as DataOfActivity),
                    MyInfo.tag,
                )
                addToBackStack(null)
                commit()
            }
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        App.INSTANCE.db.activityDao().getAll().observe(viewLifecycleOwner) {
            activities.clear()
            data_activities.clear()
            for(activity in it) {
                var startDate = LocalDateTime.ofInstant(Instant.ofEpochMilli(activity.dateStart), ZoneId.systemDefault())
                var endDate = LocalDateTime.ofInstant(Instant.ofEpochMilli(activity.dateEnd), ZoneId.systemDefault())
                var type = ActivityEnums.values()[activity.type].type
                var distance = (1..20).random().toString() + " км"
                activities.add(DataOfActivity(distance, type, startDate, endDate))
            }
            fillDate(activities)
            adapter.notifyDataSetChanged()
        }
        val recycleView = binding.recyclerTrackerActivity
        recycleView.layoutManager = LinearLayoutManager(requireContext())
        recycleView.adapter = adapter
        adapter.setItemClickListener { changeFragment(it) }
        binding.startNewActivity.setOnClickListener{
            val manager = activity?.supportFragmentManager?.findFragmentByTag(ActivityTab.tag)?.childFragmentManager
            val navbar = activity?.findViewById<BottomNavigationView>(R.id.bottomNavigationView)
            navbar?.visibility = View.GONE
            manager?.beginTransaction()?.apply {
                manager.fragments.forEach(::hide)
                add(R.id.tab_fragment_container, NewActivityFragment.newInstance(), NewActivityFragment.tag)
                addToBackStack(null)
                commit()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}