package com.example.hw_14.View


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hw_14.Model.LikusData
import com.example.hw_14.databinding.ItemLayoutBinding


class Adapter: ListAdapter<LikusData.Content, Adapter.ApartmentViewHolder>(ItemCallback()) {

    var clickListener: ((LikusData.Content) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ApartmentViewHolder(
        ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ApartmentViewHolder, position: Int) {
        holder.bind()
    }

    inner class ApartmentViewHolder(private val binding: ItemLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val apartment = getItem(adapterPosition)
            binding.apply {
                Glide.with(this.pic)
                    .load(apartment.cover)
                    .into(pic)
                title.text = apartment.titleKA
                description.text = apartment.descriptionKA?.trim()
                publishdate.text = apartment.publishDate
                this.root.setOnClickListener {
                    clickListener?.invoke(apartment)
                }
            }
        }
    }

    private class ItemCallback: DiffUtil.ItemCallback<LikusData.Content>() {
        override fun areItemsTheSame(oldItem: LikusData.Content, newItem: LikusData.Content): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: LikusData.Content, newItem: LikusData.Content): Boolean {
            return oldItem == newItem
        }

    }
}