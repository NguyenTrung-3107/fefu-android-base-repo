package ru.fefu.activityapplication.screens.tracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.nearby.messages.Distance
import com.google.type.Date
import ru.fefu.activityapplication.adapters.AdapterActivityList
import ru.fefu.activityapplication.databinding.MyDetailsBinding
import ru.fefu.activityapplication.models.DataOfActivity
import java.time.Duration
import java.time.LocalDateTime

class MyInfo(private val info: DataOfActivity): Fragment() {
    private var _binding: MyDetailsBinding? = null
    private val binding get() = _binding!!
    companion object {
        fun newInstance(info: DataOfActivity): MyInfo {
            return MyInfo(info)
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
        binding.detailDistance.text = info.distance
        var startTime = "%02d".format(info.startDate.hour) + ":" + "%02d".format(info.startDate.minute)

        var endTime = "%02d".format(info.endDate.hour) + ":" + "%02d".format(info.endDate.minute)

        binding.detailStartTime.text = startTime
        binding.detailFinishTime.text = endTime

        if (LocalDateTime.now().equals(info.endDate)) {
            binding.detailDate.text = Duration.between(info.endDate, LocalDateTime.now()).toHours().toString() +
                    AdapterActivityList.getNoun(
                        Duration.between(
                            info.endDate,
                            LocalDateTime.now()
                        ).toHours(), " час", " часа", " часов"
                    ) +
                    " назад"
        }
        else binding.detailDate.text = info.endDate.dayOfMonth.toString() + '.'+
                info.endDate.monthValue.toString() + '.' + info.endDate.year.toString()
        binding.myDetailToolbar.setNavigationOnClickListener {
            activity?.onBackPressed()
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}