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
import ru.fefu.activityapplication.databinding.NewActivityFragmentBinding

class NewActivityFragment: Fragment() {
    private var _binding: NewActivityFragmentBinding? = null
    private val binding get() = _binding!!
    private val adapter = AdapterNewActivityList()

    companion object {
        fun newInstance(): NewActivityFragment {
            return NewActivityFragment()
        }
        const val tag = "new_activity"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = NewActivityFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recycleView = binding.newActivityFragmentList
        recycleView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recycleView.adapter = adapter

        binding.newActivityFragmentBackButton.setOnClickListener {
            activity?.onBackPressed()
        }

        binding.newActivityFragmentContinueButton.setOnClickListener {
            if (adapter.selected != -1) {
                val manager = activity?.supportFragmentManager?.findFragmentByTag(ActivityTab.tag)?.childFragmentManager
                manager?.beginTransaction()?.apply {
                    manager.fragments.forEach(::hide)
                    add (
                        R.id.tab_fragment_container,
                        StartedActivityFragment.newInstance(),
                        StartedActivityFragment.tag,
                    )
                    addToBackStack(null)
                    commit()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}