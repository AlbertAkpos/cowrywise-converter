package me.alberto.cowrywiseconveter.screens.history.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import me.alberto.cowrywiseconveter.data.remote.model.Query
import me.alberto.cowrywiseconveter.screens.common.fragment.ChartFragment

class FragmentAdapter(fragmentActivity: FragmentActivity, private val query: Query) :
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        val fragment = ChartFragment()
        fragment.arguments = Bundle().apply {
            putInt(HISTORY, position + 1)
            putParcelable(QUERY, query)
        }
        return fragment
    }

    companion object {
        const val HISTORY = "history_to_fetch"
        const val QUERY = "query"
    }
}