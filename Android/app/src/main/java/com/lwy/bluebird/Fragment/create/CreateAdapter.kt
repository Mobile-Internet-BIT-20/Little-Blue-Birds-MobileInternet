package com.lwy.bluebird.Fragment.publichome

import android.icu.text.SimpleDateFormat
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.lwy.bluebird.Data.User
import com.lwy.bluebird.R
import com.lwy.bluebird.databinding.ImageLayoutBinding
import com.lwy.bluebird.databinding.PostLayoutBinding

class CreateAdapter : RecyclerView.Adapter<CreateAdapter.MyViewHolder>() {

    var imageList = emptyList<Uri>()

    class MyViewHolder(internal val binding: ImageLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(image: Uri) {
            binding.createImage.setImageURI(image)
        }

        companion object{
            fun from(parent: ViewGroup): MyViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ImageLayoutBinding.inflate(layoutInflater,parent,false)
                return MyViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = imageList[position]

        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    fun setData(img: List<Uri>){
        this.imageList = img
    }

}