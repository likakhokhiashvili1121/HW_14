package com.example.hw_14.View


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hw_14.Model.LikusData
import com.example.hw_14.databinding.ItemLayoutBinding


class ApartmentAdapter: ListAdapter<LikusData.Content,ApartmentAdapter.MyViewHolder>(ItemCallback()) {

    var itemClickListener: ((LikusData.Content) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyViewHolder(
        ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind()
    }

    inner class MyViewHolder(private val binding: ItemLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val content = currentList[adapterPosition]
            binding.apply {
                Glide.with(this.pic).load(content.cover).into(pic)

                title.text = content.titleKA
                description.text = content.descriptionKA
                publishdate.text = content.publishDate
                root.setOnClickListener {
                    itemClickListener?.invoke(content)
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