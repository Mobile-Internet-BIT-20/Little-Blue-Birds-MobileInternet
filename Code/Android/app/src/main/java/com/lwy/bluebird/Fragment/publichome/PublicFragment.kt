package com.lwy.bluebird.Fragment.publichome

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.lwy.bluebird.Data.postPublic
import com.lwy.bluebird.R
import com.lwy.bluebird.databinding.FragmentPublicBinding

class PublicFragment : Fragment() {

    private  var _binding: FragmentPublicBinding? = null
    private val binding: FragmentPublicBinding
        get() = _binding!!

    private val adapter: PublicAdapter by lazy{
        PublicAdapter()
    }

    private val posts = mutableListOf<postPublic>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPublicBinding.inflate(inflater, container, false)

        binding.rvPublic.adapter = adapter
        binding.rvPublic.layoutManager = StaggeredGridLayoutManager(2, LinearLayout.VERTICAL)

        posts.add(postPublic(R.drawable.pic1,"超好看设计！！！！"))
        posts.add(postPublic(R.drawable.pic2, "只有波涛汹涌的大海，才能创造出沙滩的光洁于柔软"))
        posts.add(postPublic(R.drawable.pic3, "一个人的旅行"))
        posts.add(postPublic(R.drawable.pic5, "我想站在城市的最高点,俯瞰这城市的繁华,我想站在城市的一角,遥望这城市的无数繁灯。"))
        posts.add(postPublic(R.drawable.pic6, "不必慌张，活好当下，来日方长；不必失望，人间值得，未来可期。"))
        posts.add(postPublic(R.drawable.pic7, "哒咩!"))

        adapter.setData(posts)

        return binding.root
    }
}