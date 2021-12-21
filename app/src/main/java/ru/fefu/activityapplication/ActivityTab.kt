package ru.fefu.activityapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.fefu.activityapplication.databinding.TabActivityFragmentBinding

class ActivityTab: Fragment() {
    private var _binding: TabActivityFragmentBinding? = null

    private val binding get() = _binding!!

    companion object {
        fun newInstance(): ActivityTab {
            return ActivityTab()
        }
        const val tag = "tabs"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = TabActivityFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState == null) {
            childFragmentManager.beginTransaction().apply {
                add(R.id.tab_fragment_container, TrackerActivityFragment.newInstance())
                commit()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}