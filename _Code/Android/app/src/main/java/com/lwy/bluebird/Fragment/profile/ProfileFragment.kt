package com.lwy.bluebird.Fragment.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import com.lwy.bluebird.R
import com.lwy.bluebird.databinding.FragmentProfileBinding
import kotlinx.coroutines.handleCoroutineException

class ProfileFragment : Fragment() {

    private  var _binding: FragmentProfileBinding? = null
    private val binding: FragmentProfileBinding
        get() = _binding!!

    val fragmentList = mutableListOf<Fragment>(
        MyPostFragment(), LikesFragment()
    )
    val titleList = mutableListOf<String>(
        "笔记", "赞过"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        val adapter = ProfileAdapter(this, fragmentList, titleList)
        binding.viewpager.adapter = adapter
        TabLayoutMediator(binding.tabs, binding.viewpager){
            tab, position ->
                tab.text = adapter.getPageTitle(position)
        }.attach()

        binding.editButton.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_editFragment)
        }

        return binding.root
    }
}