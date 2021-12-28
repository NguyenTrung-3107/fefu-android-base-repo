package ru.fefu.activityapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import ru.fefu.activityapplication.databinding.TrackingAcitivityFragmentBinding

class TrackingFragment : Fragment() {
    private var _binding: TrackingAcitivityFragmentBinding? = null

    private val binding get() = _binding!!
    private lateinit var adapter: PagerAdapter

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
        _binding = TrackingAcitivityFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = PagerAdapter(this)
        binding.pagerTrackingFragment.adapter = adapter

        TabLayoutMediator(binding.tabLayoutTrackingFragment, binding.pagerTrackingFragment) {tab, position ->
            if (position == 0) {
                tab.text = "My"
            } else {
                tab.text = "Other"
            }
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}