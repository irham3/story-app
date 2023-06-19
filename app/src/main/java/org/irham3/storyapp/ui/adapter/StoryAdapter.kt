package org.irham3.storyapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.irham3.storyapp.data.remote.response.ListStoryItem
import org.irham3.storyapp.databinding.ListItemBinding

class StoryAdapter : ListAdapter<ListStoryItem, StoryAdapter.ViewHolder>(DIFF_UTIL) {
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ListItemBinding.inflate(
                LayoutInflater.from(viewGroup.context), viewGroup, false
            )
        )
    }
    override fun onBindViewHolder(view: ViewHolder, position: Int) = with(view){
        bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(storyItem: ListStoryItem) {
            binding.apply {
                tvItemTitle.text = storyItem.name
            }

            Glide.with(itemView.context)
                .load(storyItem.photoUrl)
                .into(binding.ivItemImage)

            itemView.setOnClickListener {

            }
        }
    }

    companion object {
        private val DIFF_UTIL = object : DiffUtil.ItemCallback<ListStoryItem>() {
            override fun areItemsTheSame(oldItem: ListStoryItem, newItem: ListStoryItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ListStoryItem, newItem: ListStoryItem): Boolean {
                return oldItem == newItem
            }
        }
    }

}