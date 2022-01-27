package ru.fefu.activityapplication.adapters

import ru.fefu.activityapplication.screens.tracker.TrackingFragment

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.fefu.activityapplication.screens.tracker.TrackerActivityFragment
import ru.fefu.activityapplication.screens.tracker.TrackerActivityFragmentOther

class AdapterPager(fragment: TrackingFragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2
    override fun createFragment(position: Int): Fragment {
        return if (position == 0) {
           TrackerActivityFragment()
        } else {
            TrackerActivityFragmentOther()
        }
    }
}