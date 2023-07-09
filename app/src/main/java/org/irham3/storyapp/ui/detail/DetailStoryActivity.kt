package org.irham3.storyapp.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.irham3.storyapp.databinding.ActivityDetailStoryBinding

@AndroidEntryPoint
class DetailStoryActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_ID = "extra_id"
    }

    private lateinit var binding: ActivityDetailStoryBinding
    private val detailStoryViewModel: DetailStoryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailStoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val storyId = intent.getStringExtra(EXTRA_ID)!!

        CoroutineScope(Dispatchers.Main).launch {
            val detailData = detailStoryViewModel.getDetailStory(storyId)
            binding.apply {
                Glide.with(root.context)
                    .load(detailData.photoUrl)
                    .into(ivDetailPhoto)

                tvDetailName.text = detailData.name
                tvDetailDescription.text = detailData.description
            }
        }

    }
}