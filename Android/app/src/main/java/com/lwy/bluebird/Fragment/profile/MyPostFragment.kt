package com.lwy.bluebird.Fragment.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.lwy.bluebird.Data.postPublic
import com.lwy.bluebird.Fragment.publichome.PublicAdapter
import com.lwy.bluebird.R
import com.lwy.bluebird.databinding.FragmentMyPostBinding

class MyPostFragment : Fragment() {

    private  var _binding: FragmentMyPostBinding? = null
    private val binding: FragmentMyPostBinding
        get() = _binding!!

    private val adapter: PublicAdapter by lazy{
        PublicAdapter()
    }

    private val posts = mutableListOf<postPublic>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMyPostBinding.inflate(inflater, container, false)

        binding.rvMyPost.adapter = adapter
        binding.rvMyPost.layoutManager = StaggeredGridLayoutManager(2, LinearLayout.VERTICAL)

        posts.add(postPublic(R.drawable.pic6, "不必慌张，活好当下，来日方长；不必失望，人间值得，未来可期。"))
        posts.add(postPublic(R.drawable.pic7, "哒咩!"))

        adapter.setData(posts)

        return binding.root
    }
}