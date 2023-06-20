package org.irham3.storyapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.irham3.storyapp.data.remote.response.StoryItem
import org.irham3.storyapp.databinding.ListItemBinding

class StoryAdapter(private var listData: List<StoryItem>) :
    RecyclerView.Adapter<StoryAdapter.ViewHolder>(), Comparator<StoryItem> {
    var onItemClick: ((StoryItem) -> Unit)? = null
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ListItemBinding.inflate(
                LayoutInflater.from(viewGroup.context), viewGroup, false
            )
        )
    }

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(view: ViewHolder, position: Int) = with(view) {
        bind(listData[position])
    }

    inner class ViewHolder(private val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(storyItem: StoryItem) {
            binding.apply {
                tvItemTitle.text = storyItem.name
            Glide.with(root.context)
                .load(storyItem.photoUrl)
                .into(ivItemImage)
            }


//            itemView.setOnClickListener {
//
//            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[bindingAdapterPosition])
            }
        }
    }

    override fun compare(p0: StoryItem, p1: StoryItem) = p0.id.compareTo(p1.id)

}