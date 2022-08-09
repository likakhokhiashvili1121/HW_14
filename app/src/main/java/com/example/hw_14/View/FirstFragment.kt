package com.example.hw_14.View

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hw_14.Model.LikusData
import com.example.hw_14.ResponseState
import com.example.hw_14.ViewModel.ApartmentViewModel
import com.example.hw_14.databinding.FragmentFirstBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class FirstFragment : Fragment() {


    private var _binding: FragmentFirstBinding? = null

    private val binding get() = _binding!!

    private val Adapter by lazy {
        Adapter()
    }

    private val viewModel: ApartmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        listeners()
        observers()
    }

    private fun init() {
        binding.recyclerView.apply {
            adapter = Adapter
            layoutManager = LinearLayoutManager(context)
        }
        viewModel.infoGet()
    }

    private fun observers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.likusflow.collect { likusstate ->
                    when(likusstate) {
                        is ResponseState.Success -> success(likusstate.resultList)
                        is ResponseState.Error -> error1(likusstate.errorBody)
                        else -> {}
                    }
                    binding.progressBar.isVisible = likusstate.isLoading
                }
            }
        }
    }

    private fun error1(errorBody: String) {
        Snackbar.make(binding.root, errorBody, Snackbar.LENGTH_SHORT).show()
    }

    private fun success(resultList: List<LikusData.Content>) {
        Adapter.submitList(resultList)
    }

    private fun listeners() {
        Adapter.clickListener = {
            findNavController().navigate(FirstFragmentDirections.actionFirstFragmentToSecondFragment(it))

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}