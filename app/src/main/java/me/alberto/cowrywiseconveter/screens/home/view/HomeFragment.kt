package me.alberto.cowrywiseconveter.screens.home.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import me.alberto.cowrywiseconveter.App
import me.alberto.cowrywiseconveter.R
import me.alberto.cowrywiseconveter.data.remote.model.Query
import me.alberto.cowrywiseconveter.databinding.FragmentHomeBinding
import me.alberto.cowrywiseconveter.screens.history.view.HistoryFrag
import me.alberto.cowrywiseconveter.screens.home.viewmodel.HomeViewModel
import me.alberto.cowrywiseconveter.util.LoadingState
import java.util.*
import javax.inject.Inject
import kotlin.concurrent.schedule

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    private val TAG = "HomeFragment"

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val fadeInAnim by lazy {
        AnimationUtils.loadAnimation(
            requireContext(),
            R.anim.fade_in
        )
    }
    private val fadeOutAnim by lazy {
        AnimationUtils.loadAnimation(
            requireContext(),
            R.anim.fade_out
        )
    }

    private val viewModel: HomeViewModel by viewModels { factory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        setupDrawerWithToolbar()
        setupViewModel()
        setupObservers()
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClickListeners()
    }

    private fun setClickListeners() {
        binding.convertBtn.setOnClickListener {
            viewModel.convert()
        }
        binding.loadingLayout.retry.setOnClickListener {
            viewModel.getSymbols()
        }

        binding.rateHistory.setOnClickListener {
            val showHistoryFrag =
                (viewModel.showHistory.value != null && viewModel.showHistory.value != false)
            if (showHistoryFrag) {
                showBottomSheet()
            } else {
                Toast.makeText(requireContext(), "Please pick target currencies", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

    private fun setupViewModel() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }

    private fun setupObservers() {
        viewModel.error.observe(viewLifecycleOwner) {
            binding.errorText.text = it
            binding.errorText.startAnimation(fadeInAnim)
            Timer().schedule(3000) {
                requireActivity().runOnUiThread {
                    binding.errorText.startAnimation(fadeOutAnim)
                    binding.errorText.text = ""
                }
            }
        }

        viewModel.loadingState.observe(viewLifecycleOwner) { loadingState ->
            loadingState ?: return@observe
            when (loadingState) {
                is LoadingState.Success -> {
                    binding.loadingLayout.root.visibility = View.GONE
                }
                is LoadingState.Error -> {
                    binding.loadingLayout.errorContainer.visibility = View.VISIBLE
                    binding.loadingLayout.errorText.text = loadingState.error
                    binding.loadingLayout.progressBar.visibility = View.GONE
                }
                is LoadingState.Loading -> {
                    binding.loadingLayout.progressBar.visibility = View.VISIBLE
                    binding.loadingLayout.errorContainer.visibility = View.GONE
                }
            }

        }

        viewModel.showHistory.observe(viewLifecycleOwner) {
            if (it == true) {
                binding.rateHistory.setTextColor(resources.getColor(R.color.app_blue))
            }
        }
    }

    private fun showBottomSheet() {
        val from = binding.fromEdittext.text.toString()
        val to = binding.toEdittext.text.toString()
        val query = Query(from, to, 0.0)
        val dialog = HistoryFrag.newInstance(query)
        dialog.show(requireActivity().supportFragmentManager, dialog.javaClass.name)
    }

    private fun setupDrawerWithToolbar() {
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        binding.toolbar.title = ""
        val drawerToggle = ActionBarDrawerToggle(
            requireActivity(), binding.drawerLayout, binding.toolbar,
            R.string.open, R.string.close
        )
        binding.drawerLayout.addDrawerListener(drawerToggle)
        drawerToggle.drawerArrowDrawable.color = resources.getColor(R.color.app_green)
        drawerToggle.syncState()

    }


}