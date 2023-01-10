package com.lwy.bluebird.Fragment.create

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lwy.bluebird.R
import com.lwy.bluebird.databinding.FragmentCreateBinding
import com.lwy.bluebird.databinding.FragmentProfileBinding

class CreateFragment : Fragment() {

    private  var _binding: FragmentCreateBinding? = null
    private val binding: FragmentCreateBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCreateBinding.inflate(inflater, container, false)

        return binding.root
    }
}