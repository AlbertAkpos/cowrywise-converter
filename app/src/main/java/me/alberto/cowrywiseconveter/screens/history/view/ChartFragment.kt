package me.alberto.cowrywiseconveter.screens.history.view

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IFillFormatter
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import me.alberto.cowrywiseconveter.App
import me.alberto.cowrywiseconveter.R
import me.alberto.cowrywiseconveter.data.remote.model.Query
import me.alberto.cowrywiseconveter.databinding.FragmentChartBinding
import me.alberto.cowrywiseconveter.screens.history.adapter.FragmentAdapter
import me.alberto.cowrywiseconveter.screens.history.viewmodel.ChartFragmentViewModel
import me.alberto.cowrywiseconveter.util.XAxisValueFormatter
import javax.inject.Inject

class ChartFragment : Fragment() {
    private lateinit var binding: FragmentChartBinding

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private lateinit var lineChart: LineChart

    private val viewModel: ChartFragmentViewModel by viewModels { factory }

    private val historyToFetch by lazy { arguments?.getInt(FragmentAdapter.HISTORY) }
    private val query: Query? by lazy { arguments?.getParcelable(FragmentAdapter.QUERY) }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChartBinding.inflate(inflater, container, false)
        initViews()
        setupObservers()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (query != null && historyToFetch != null) {
            viewModel.getRateHistory(query!!, historyToFetch!!)
        }
    }

    private fun initViews() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        lineChart = binding.lineChart
        lineChart.setViewPortOffsets(0f, 0f, 0f, 0f)
        lineChart.setBackgroundColor(resources.getColor(R.color.app_blue))
        lineChart.description.isEnabled = false

        lineChart.setTouchEnabled(true)

        lineChart.isDragEnabled = true
        lineChart.setScaleEnabled(true)

        lineChart.setDrawGridBackground(false)
        lineChart.maxHighlightDistance = 300f //change

        val xAxis = lineChart.xAxis
        xAxis.isEnabled = false

        val yAxis = lineChart.axisLeft
        yAxis.setLabelCount(6, false)
        yAxis.textColor = Color.WHITE
        yAxis.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART)
        yAxis.setDrawGridLines(false)
        yAxis.axisLineColor = Color.WHITE

        lineChart.axisRight.isEnabled = false

        lineChart.legend.isEnabled = false
        lineChart.animateXY(2000, 2000)

        lineChart.invalidate()

    }

    private fun setupObservers() {
        viewModel.rates.observe(viewLifecycleOwner) { rates ->
            rates ?: return@observe
            val dates = rates.map { it.key }
            val rateValues = rates.map { it.value }
            drawChart(dates, rateValues)
        }

        viewModel.error.observe(viewLifecycleOwner){
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        }
    }

   private fun drawChart(dateList: List<String>, rates: List<Double>) {

        val values = ArrayList<Entry>()
        for (i in rates.indices) {
            values.add(Entry(i.toFloat(), rates[i].toFloat()))
        }

        val set1: LineDataSet

        if (lineChart.data != null && lineChart.data.dataSetCount > 0) {
            set1 = lineChart.data.getDataSetByIndex(0) as LineDataSet
            set1.values = values
            lineChart.data.notifyDataChanged()
            lineChart.notifyDataSetChanged()
        } else {
            set1 = LineDataSet(values, "The label")
            set1.mode = LineDataSet.Mode.CUBIC_BEZIER
            set1.cubicIntensity = 0.2f
            set1.setDrawFilled(true)
            set1.setDrawCircles(false)
            set1.lineWidth = 1.8f
            set1.circleRadius = 4f
            set1.setCircleColor(Color.WHITE)
            set1.highLightColor = resources.getColor(R.color.app_red)
            set1.setColor(Color.WHITE)
            set1.fillColor = Color.WHITE
            set1.fillAlpha = 100
            set1.setDrawHorizontalHighlightIndicator(false)
            set1.fillFormatter =
                IFillFormatter { dataSet, dataProvider -> lineChart.axisLeft.axisMinimum }

            val data = LineData(set1)
            data.setValueTextSize(9f)
            data.setDrawValues(false)
            lineChart.data = data
        }

       lineChart.invalidate()
    }


}