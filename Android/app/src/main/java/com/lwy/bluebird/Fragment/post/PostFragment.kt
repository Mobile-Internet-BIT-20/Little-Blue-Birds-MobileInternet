package com.lwy.bluebird.Fragment.post

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.lwy.bluebird.Fragment.publichome.PublicAdapter
import com.lwy.bluebird.R
import com.lwy.bluebird.databinding.FragmentPostBinding

class PostFragment : Fragment() {

    private  var _binding: FragmentPostBinding? = null
    private val binding: FragmentPostBinding
        get() = _binding!!

    private var imagesList = mutableListOf<Uri>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPostBinding.inflate(inflater, container, false)

        binding.viewpagerPost.adapter = PostImageAdapter(imagesList)
        binding.viewpagerPost.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        binding.circleIndicator.setViewPager(binding.viewpagerPost)

        binding.buttonBack.setOnClickListener {
            findNavController().navigate(R.id.action_postFragment_to_publicFragment)
        }

        binding.buttonLikes.setOnClickListener {
            binding.buttonLikes.setBackgroundResource(R.drawable.ic_fav)
        }

        return binding.root
    }

}