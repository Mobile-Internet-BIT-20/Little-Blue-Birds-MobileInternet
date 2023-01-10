package com.lwy.bluebird.Fragment.publichome

import android.icu.text.SimpleDateFormat
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.lwy.bluebird.Data.bbData
import com.lwy.bluebird.R
import com.lwy.bluebird.databinding.PostLayoutBinding

class PublicAdapter : RecyclerView.Adapter<PublicAdapter.MyViewHolder>() {

    var dataList = emptyList<bbData>()

    class MyViewHolder(internal val binding: PostLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: bbData) {
            binding.postLayoutImage.setImageResource(data.image)
            binding.postLayoutTitle.text = data.title
        }

        companion object{
            fun from(parent: ViewGroup): MyViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = PostLayoutBinding.inflate(layoutInflater,parent,false)
                return MyViewHolder(binding)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = dataList[position]

        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun setData(data: List<bbData>){
        this.dataList = data
    }
}