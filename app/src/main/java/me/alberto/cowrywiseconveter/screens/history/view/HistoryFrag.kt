package me.alberto.cowrywiseconveter.screens.history.view

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.google.android.material.bottomsheet.BottomSheetDialog
import me.alberto.cowrywiseconveter.R
import me.alberto.cowrywiseconveter.data.remote.model.Query
import me.alberto.cowrywiseconveter.databinding.FragmentHistoryBinding
import me.alberto.cowrywiseconveter.screens.history.adapter.FragmentAdapter


class HistoryFrag : DialogFragment() {


    private lateinit var binding: FragmentHistoryBinding
    private val query by lazy {
        arguments?.getParcelable<Query>(FragmentAdapter.QUERY)
    }

    companion object {
        fun newInstance(query: Query): HistoryFrag {
            val fragment = HistoryFrag()
            val bundle = Bundle().apply { putParcelable(FragmentAdapter.QUERY, query) }
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        binding = FragmentHistoryBinding.inflate(LayoutInflater.from(context), null, false)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(requireContext())
        dialog.setContentView(binding.root)
        return dialog
    }


}