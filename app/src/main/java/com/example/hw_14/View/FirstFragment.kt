package com.example.hw_14.View

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hw_14.Model.LikusData
import com.example.hw_14.ViewModel.ApartmentViewModel
import com.example.hw_14.databinding.FragmentFirstBinding
import kotlinx.coroutines.launch

class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    private val apartAdapter by lazy {
        ApartmentAdapter()
    }

    private val viewModel: ApartmentViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        SetView()
        onItemClick()
        startInfoGet()

    }


    private fun SetView() {
        binding.recyclerView.apply {
            adapter = apartAdapter
            layoutManager = LinearLayoutManager(context)
        }
        viewModel.infoGet()
    }

    private fun onItemClick() {
        apartAdapter.itemClickListener = {
            findNavController().navigate(
                FirstFragmentDirections.actionFirstFragmentToSecondFragment(it)
            )
        }
    }

    private fun startInfoGet() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.likusFlow.collect {
                    successCatch(it.content)
                }
            }

        }
    }


    private fun successCatch(resultList: List<LikusData.Content>) {
        apartAdapter.submitList(resultList)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}