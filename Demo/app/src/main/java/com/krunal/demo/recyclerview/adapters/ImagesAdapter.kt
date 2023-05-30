package com.krunal.demo.recyclerview.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.krunal.demo.databinding.ImageLayoutBinding

class ImagesAdapter : RecyclerView.Adapter<ImagesAdapter.ImageViewHolder>() {

    private val images: MutableList<Int> = mutableListOf()
    var onLongClick: ((position: Int) -> Unit)? = null

    class ImageViewHolder(private val binding: ImageLayoutBinding, private val onLongClick: ((position: Int) -> Unit)?): RecyclerView.ViewHolder(binding.root) {
        fun bind(imageResource: Int) {
            binding.imgView.setImageResource(imageResource)
            binding.root.setOnLongClickListener {
                onLongClick?.invoke(adapterPosition)
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ImageLayoutBinding.inflate(layoutInflater)
        return ImageViewHolder(binding, onLongClick)
    }

    override fun getItemCount(): Int = images.count()

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(images[position])
    }

    fun submitList(list: List<Int>) {
        images.clear()
        images.addAll(list)
        notifyDataSetChanged()
    }
}