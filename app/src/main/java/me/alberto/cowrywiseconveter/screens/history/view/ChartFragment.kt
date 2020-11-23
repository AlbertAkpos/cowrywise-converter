package me.alberto.cowrywiseconveter.screens.history.view

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import me.alberto.cowrywiseconveter.R
import me.alberto.cowrywiseconveter.data.remote.model.Query
import me.alberto.cowrywiseconveter.databinding.FragmentChartBinding
import me.alberto.cowrywiseconveter.screens.history.adapter.FragmentAdapter
import me.alberto.cowrywiseconveter.screens.history.viewmodel.ChatFragmentViewModel
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class ChartFragment : Fragment() {
    private lateinit var binding: FragmentChartBinding

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private lateinit var lineChart: LineChart

    private val viewModel: ChatFragmentViewModel by viewModels { factory }

    private val historyToFetch by lazy { arguments?.getInt(FragmentAdapter.HISTORY) }
    private val query: Query? by lazy { arguments?.getParcelable(FragmentAdapter.QUERY) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChartBinding.inflate(inflater, container, false)
        initViews()
        return binding.root
    }

    private fun initViews() {
        lineChart = binding.lineChart
        lineChart.setViewPortOffsets(0f, 0f, 0f, 0f)
        lineChart.setBackgroundColor(resources.getColor(R.color.app_blue))
        lineChart.setTouchEnabled(true)
        lineChart.isDragEnabled = true
        lineChart.setScaleEnabled(true)
    }

    fun drawChart(dateList: List<String>, rates: List<Double>) {
        val xAxis = lineChart.xAxis
        val yAxis = lineChart.axisLeft

        val position = XAxis.XAxisPosition.BOTTOM
        xAxis.position = position
        xAxis.axisMaximum = 5f
        xAxis.axisMinimum = 0f
        xAxis.setLabelCount(6, true);
        xAxis.isGranularityEnabled = true;
        xAxis.granularity = 7f
        xAxis.labelRotationAngle = 315f
        xAxis.valueFormatter = XAxisValueFormatter(dateList)

        //Y axis
        yAxis.axisMaximum = (rates.maxOrNull()?.toFloat() ?: 0f) + 100f
        yAxis.axisMinimum = 0f
        yAxis.setDrawZeroLine(false)

        val values = ArrayList<Entry>()
        for (i in rates.indices) {
            values.add(Entry(i.toFloat(), rates[i].toFloat()))
        }

        val set1: LineDataSet

        if (lineChart.data != null && lineChart.data.dataSetCount > 0){
            set1 = lineChart.data.getDataSetByIndex(0) as LineDataSet
            set1.values = values
            lineChart.notifyDataSetChanged()
        } else {
            set1 = LineDataSet(values, "The label")
            set1.setDrawCircles(true)
            set1.setColor(Color.RED)
            set1.valueTextSize = 10f
            set1.formLineWidth = 5f
            set1.fillColor = Color.RED
            set1.setDrawValues(true)
            val dataSets = ArrayList<ILineDataSet>()
            dataSets.add(set1)
            val data = LineData(dataSets)
            lineChart.data = data
        }


    }

    class XAxisValueFormatter(private val dateList: List<String>) : ValueFormatter() {
        override fun getAxisLabel(value: Float, axis: AxisBase?): String {
            var position = Math.round(value)
            val sdf = SimpleDateFormat("MMM dd", Locale.ENGLISH)

            if (value > 1 && value < 2) {
                position = 0
            } else if (value > 2 && value < 3) {
                position = 1
            } else if (value > 3 && value < 4) {
                position = 2
            } else if (value > 4 && value <= 5) {
                position = 3
            }
            return if (position < dateList.size) sdf.format(
                Date(
                    getDateInMilliSeconds(
                        dateList.get(position),
                        "yyyy-MM-dd"
                    )
                )
            ) else ""
        }

        private fun getDateInMilliSeconds(dateString: String, format: String): Long {
            val sdf = SimpleDateFormat(format, Locale.US)
            var timeInMilliseconds: Long = 1
            try {
                val mDate = sdf.parse(dateString)
                timeInMilliseconds = mDate.time
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            return timeInMilliseconds
        }

    }

}