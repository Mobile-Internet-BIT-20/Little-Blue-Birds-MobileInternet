package com.lwy.bluebird.Fragment.edit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.lwy.bluebird.R
import com.lwy.bluebird.databinding.FragmentCreateBinding
import com.lwy.bluebird.databinding.FragmentEditBinding

class EditFragment : Fragment() {

    private  var _binding: FragmentEditBinding? = null
    private val binding: FragmentEditBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditBinding.inflate(inflater, container, false)

        binding.buttonDone.setOnClickListener {
            requireActivity().onBackPressed()
        }

        return binding.root
    }
}