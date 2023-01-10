package com.lwy.bluebird.Fragment.publichome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.lwy.bluebird.R
import com.lwy.bluebird.databinding.FragmentPublicBinding

class PublicFragment : Fragment() {

    private  var _binding: FragmentPublicBinding? = null
    private val binding: FragmentPublicBinding
        get() = _binding!!

    private val adapter: PublicAdapter by lazy{
        PublicAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPublicBinding.inflate(inflater, container, false)

        binding.rvPublic.adapter = adapter
        binding.rvPublic.layoutManager = StaggeredGridLayoutManager(2,LinearLayout.VERTICAL)

        return binding.root
    }

}