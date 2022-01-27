package ru.fefu.activityapplication.screens.tracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import ru.fefu.activityapplication.adapters.AdapterPager
import ru.fefu.activityapplication.databinding.TrackingActivityFragmentBinding

class TrackingFragment : Fragment() {
    private var _binding: TrackingActivityFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter : AdapterPager
    companion object {
        fun newInstance(): TrackingFragment {
            return TrackingFragment()
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = TrackingActivityFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = AdapterPager(this)
        binding.pagerTrackingFragment.adapter = adapter
        TabLayoutMediator(binding.tabLayoutTrackingFragment, binding.pagerTrackingFragment) {tab, position ->
            if (position == 0) {
                tab.text = "Мои"
            }
            else {
                tab.text = "Пользователей"
            }
        }.attach()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}