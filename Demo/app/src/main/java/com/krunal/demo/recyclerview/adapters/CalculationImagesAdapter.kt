package com.krunal.demo.recyclerview.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.krunal.demo.databinding.ImageLayoutBinding

class CalculationImagesAdapter : RecyclerView.Adapter<CalculationImagesAdapter.ImageViewHolder>() {

    private val images: MutableList<Uri> = mutableListOf()
    var onLongClick: ((position: Int) -> Unit)? = null

    class ImageViewHolder(private val binding: ImageLayoutBinding, private val onLongClick: ((position: Int) -> Unit)?): RecyclerView.ViewHolder(binding.root) {
        fun bind(uri: Uri) {
            binding.imgView.setImageURI(uri)
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

    fun submitList(list: List<Uri>) {
        images.clear()
        images.addAll(list)
        notifyDataSetChanged()
    }

    fun addImage(uri: Uri) {
        images.add(uri)
        notifyItemInserted(images.count() - 1)
    }

    fun removeImage(position: Int) {
        images.removeAt(position)
        notifyItemRemoved(position)
    }
}