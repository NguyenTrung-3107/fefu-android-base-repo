package ru.fefu.activityapplication.screens.tracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.fefu.activityapplication.R
import ru.fefu.activityapplication.databinding.ProfileActivityFragmentBinding


class ProfileFragment : Fragment() {
    private var _binding: ProfileActivityFragmentBinding? = null
    private val binding get() = _binding!!
    companion object {
        fun newInstance(): ProfileFragment {
            return ProfileFragment()
        }
        const val tag = "profile"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ProfileActivityFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState == null) {
            childFragmentManager.beginTransaction().apply {
                add(R.id.profile_fragment_container, ViewingProfileFragment.newInstance(), ViewingProfileFragment.tag)
                commit()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}