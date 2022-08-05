package com.example.hw_14.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.hw_14.Model.LikusData
import com.example.hw_14.databinding.FragmentSecondBinding


class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

    private val args: SecondFragmentArgs by navArgs()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding  = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()

    }

    private fun init() = with(binding) {
        val apartment = args.liku as LikusData.Content
        Glide.with(picfr).load(apartment.cover).into(picfr)

        titlefr.text = apartment.titleKA
        descriptionfr.text = apartment.descriptionKA
        publishdatefr.text = apartment.publishDate
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    /*override fun init(){
      val content = args.content as likusData.Content
      setData(content)
  }
  private fun setData(content:likusData.Content)
  {
      binding.apply {
          titlefr.text = content.publishDate.toString()
          descriptionfr.text = content.descriptionKA
          titlefr.text = content.titleKA
      }
  } */

}