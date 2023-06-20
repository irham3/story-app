package org.irham3.storyapp.ui.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.irham3.storyapp.data.remote.response.StoryItem
import org.irham3.storyapp.databinding.ListItemBinding

class StoryAdapter(private var listData: List<StoryItem>) :
    RecyclerView.Adapter<StoryAdapter.ViewHolder>(), Comparator<StoryItem> {
    var onItemClick: ((StoryItem, ActivityOptionsCompat) -> Unit)? = null
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
        }

        init {
            binding.root.setOnClickListener {
                val optionsCompat: ActivityOptionsCompat =
                    ActivityOptionsCompat.makeSceneTransitionAnimation(
                        itemView.context as Activity,
                        Pair(binding.ivItemImage, "photo"),
                        Pair(binding.tvItemTitle, "name")
                    )
                onItemClick?.invoke(listData[bindingAdapterPosition], optionsCompat)
            }
        }
    }

    override fun compare(p0: StoryItem, p1: StoryItem) = p0.id.compareTo(p1.id)

}