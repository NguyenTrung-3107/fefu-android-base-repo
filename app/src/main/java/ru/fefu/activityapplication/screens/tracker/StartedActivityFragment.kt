package ru.fefu.activityapplication.screens.tracker

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import ru.fefu.activityapplication.adapters.AdapterNewActivityList
import ru.fefu.activityapplication.R
import ru.fefu.activityapplication.databinding.StartedActivityFragmentBinding

class StartedActivityFragment: Fragment() {
    private var _binding: StartedActivityFragmentBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance(): StartedActivityFragment {
            return StartedActivityFragment()
        }
        const val tag = "started_activity"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = StartedActivityFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.backButton.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}