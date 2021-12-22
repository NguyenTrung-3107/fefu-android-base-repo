package ru.fefu.activityapplication.screens.tracker

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import ru.fefu.activityapplication.models.UserData
import ru.fefu.activityapplication.R
import ru.fefu.activityapplication.adapters.AdapterActivityList
import ru.fefu.activityapplication.databinding.OtherTrackerActivityFragmentBinding
import ru.fefu.activityapplication.models.DateTimeData
import java.time.LocalDateTime

class TrackerActivityFragmentOther : Fragment(R.layout.other_tracker_activity_fragment) {
    private var _binding: OtherTrackerActivityFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var items: MutableList<UserData>
    private val activities = listOf<UserData>(
        UserData(
            "1000 м",
            "Серфинг",
            LocalDateTime.of(2021, 10, 27, 11, 22),
            LocalDateTime.of(2021, 10, 28, 12, 40),
            "@user1",
        ),
        UserData(
            "14.32 км",
            "Велосипед",
            LocalDateTime.of(2021, 10, 27, 7, 40),
            LocalDateTime.of(2021, 10, 27, 10, 59),
            "user2"
        )
    )
    private val map = mapOf(1 to "Январь", 2 to "Февраль", 3 to "Март",
        4 to "Апрель", 5 to "Май", 6 to "Июнь",
        7 to "Июль", 8 to "Август", 9 to "Сентябрь",
        10 to "Октябрь", 11 to "Ноябрь", 12 to "Декабрь")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = OtherTrackerActivityFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }
    private val dataActivities = mutableListOf<Any>()
    private fun fillDate(activities: List<UserData>) {
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
                if (date.date != map[activity.endDate.monthValue] + ' ' + activity.endDate.year.toString()  + "года") {
                    date = DateTimeData(map[activity.endDate.monthValue] + activity.endDate.year.toString())
                    dataActivities.add(date)
                }
            }
            Log.d("TAG", cur.hour.toString())
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
                    MyInfo.newInstance(),
                    MyInfo.tag,
                )
                addToBackStack(null)
                commit()
            }
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fillDate(activities)
        val recycleView = binding.recyclerOtherActivity
        recycleView.layoutManager = LinearLayoutManager(requireContext())
        recycleView.adapter = adapter
        adapter.setItemClickListener { changeFragment(it) }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}