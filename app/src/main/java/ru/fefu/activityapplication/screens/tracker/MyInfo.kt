package ru.fefu.activityapplication.screens.tracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.fefu.activityapplication.databinding.MyDetailsBinding

class MyInfo: Fragment() {
    private var _binding: MyDetailsBinding? = null
    private val binding get() = _binding!!
    companion object {
        fun newInstance(): MyInfo {
            return MyInfo()
        }
        const val tag = "my_info"
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = MyDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.myDetailToolbar.setNavigationOnClickListener {
            activity?.onBackPressed()
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}