package ru.fefu.activityapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.fefu.activityapplication.databinding.UserDetailsBinding

class UserInfo: Fragment() {
    private var _binding: UserDetailsBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance(): UserInfo {
            return UserInfo()
        }
        const val tag = "user_info"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = UserDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.userDetailToolbar.setNavigationOnClickListener {
            activity?.onBackPressed()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}