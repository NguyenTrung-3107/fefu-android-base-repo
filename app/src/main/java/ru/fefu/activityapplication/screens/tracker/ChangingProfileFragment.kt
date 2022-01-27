package ru.fefu.activityapplication.screens.tracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.fefu.activityapplication.databinding.ChangingProfileActivityBinding

class ChangingProfileFragment: Fragment() {
    private var _binding: ChangingProfileActivityBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance(): ChangingProfileFragment{
            return ChangingProfileFragment()
        }
        const val tag = "profile_change"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ChangingProfileActivityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.changingProfileBackButton.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}