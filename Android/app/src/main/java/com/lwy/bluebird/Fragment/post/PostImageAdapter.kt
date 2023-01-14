package com.lwy.bluebird.Fragment.post

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lwy.bluebird.Data.User
import com.lwy.bluebird.databinding.PostImageLayoutBinding
import com.lwy.bluebird.databinding.PostLayoutBinding

class PostImageAdapter(private var images: List<Uri>)
    : RecyclerView.Adapter<PostImageAdapter.PostImageViewHolder>(){

    class PostImageViewHolder(internal val binding: PostImageLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(img: Uri) {
            binding.postImage.setImageURI(img)
        }

        companion object{
            fun from(parent: ViewGroup): PostImageViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = PostImageLayoutBinding.inflate(layoutInflater,parent,false)
                return PostImageViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostImageViewHolder {
        return PostImageViewHolder.from(parent)
    }

    override fun getItemCount(): Int {
        return images.size
    }

    override fun onBindViewHolder(holder: PostImageViewHolder, position: Int) {
        val item = images[position]

        holder.bind(item)
    }
    }