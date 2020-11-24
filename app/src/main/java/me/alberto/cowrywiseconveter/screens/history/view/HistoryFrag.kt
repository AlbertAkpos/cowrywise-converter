package me.alberto.cowrywiseconveter.screens.history.view

import android.app.Dialog
import android.content.Context
import android.content.ContextWrapper
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import me.alberto.cowrywiseconveter.R
import me.alberto.cowrywiseconveter.data.remote.model.Query
import me.alberto.cowrywiseconveter.databinding.FragmentHistoryBinding
import me.alberto.cowrywiseconveter.screens.history.adapter.FragmentAdapter


class HistoryFrag : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentHistoryBinding
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    private val query by lazy { arguments?.getParcelable<Query>(FragmentAdapter.QUERY) }


    companion object {
        fun newInstance(query: Query): HistoryFrag {
            val fragment = HistoryFrag()
            val bundle = Bundle()
            bundle.putParcelable(FragmentAdapter.QUERY, query)
            fragment.arguments = bundle
            return fragment
        }
    }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHistoryBinding.inflate(inflater, container, false)
        val fragmentAdapter = FragmentAdapter(requireActivity(), query)
        binding.viewPager.adapter = fragmentAdapter
        viewPager = binding.viewPager
        tabLayout = binding.tabLayout
        viewPager.isUserInputEnabled = false
        setupTabWithViewPager()
        return binding.root
    }


    private fun setupTabWithViewPager() {
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> getString(R.string.last_30)
                else -> getString(R.string.last_90)
            }
        }.attach()
    }


}