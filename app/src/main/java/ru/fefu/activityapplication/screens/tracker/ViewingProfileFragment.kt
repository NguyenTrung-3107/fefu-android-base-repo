package ru.fefu.activityapplication.screens.tracker

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.fefu.activityapplication.R
import ru.fefu.activityapplication.databinding.ViewingProfileActivityBinding

class ViewingProfileFragment: Fragment() {
    private var _binding: ViewingProfileActivityBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance(): ViewingProfileFragment{
            return ViewingProfileFragment()
        }
        const val tag = "profile_view"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ViewingProfileActivityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewProfileChange.setOnClickListener{
            val manager = activity?.supportFragmentManager?.findFragmentByTag(ProfileFragment.tag)?.childFragmentManager
            manager?.beginTransaction()?.apply {
                manager.fragments.forEach(::hide)
                add (R.id.profile_fragment_container, ChangingProfileFragment.newInstance(), ChangingProfileFragment.tag)
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