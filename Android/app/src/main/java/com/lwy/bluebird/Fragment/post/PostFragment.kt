package com.lwy.bluebird.Fragment.post

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.lwy.bluebird.R
import com.lwy.bluebird.databinding.FragmentPostBinding

class PostFragment : Fragment() {

    private  var _binding: FragmentPostBinding? = null
    private val binding: FragmentPostBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPostBinding.inflate(inflater, container, false)

        binding.buttonBack.setOnClickListener {
            findNavController().navigate(R.id.action_postFragment_to_publicFragment)
        }

        return binding.root
    }

}