package org.irham3.storyapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.irham3.storyapp.data.remote.response.ListStoryItem
import org.irham3.storyapp.databinding.ListItemBinding

class StoryAdapter(private var list: List<ListStoryItem>) :
    RecyclerView.Adapter<StoryAdapter.ViewHolder>(), Comparator<ListStoryItem> {
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ListItemBinding.inflate(
                LayoutInflater.from(viewGroup.context), viewGroup, false
            )
        )
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(view: ViewHolder, position: Int) = with(view) {
        bind(list[position])
    }

    inner class ViewHolder(private val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(storyItem: ListStoryItem) {
            binding.apply {
                tvItemTitle.text = storyItem.name
            }

            Glide.with(binding.root.context)
                .load(storyItem.photoUrl)
                .into(binding.ivItemImage)

//            itemView.setOnClickListener {
//
//            }
        }
    }

    override fun compare(p0: ListStoryItem, p1: ListStoryItem) = p0.id.compareTo(p1.id)

}