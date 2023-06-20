package org.irham3.storyapp.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import org.irham3.storyapp.data.Result
import org.irham3.storyapp.databinding.ActivityDetailStoryBinding

@AndroidEntryPoint
class DetailStoryActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_TOKEN = "extra_token"
        const val EXTRA_ID = "extra_id"
    }

    private lateinit var binding: ActivityDetailStoryBinding
    private val detailStoryViewModel: DetailStoryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailStoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var authToken = intent.getStringExtra(EXTRA_TOKEN)!!
        authToken = "Bearer $authToken"
        val storyId = intent.getStringExtra(EXTRA_ID)!!

        detailStoryViewModel.getDetailStory(authToken, storyId).observe(this) { result ->
            when(result) {
                is Result.Loading -> {

                }
                is Result.Error -> {
                    Toast.makeText(this, result.message, Toast.LENGTH_LONG).show()
                }
                is Result.Success -> {
                    val detailData = result.data?.storyResponse
                    binding.apply {
                        tvDetailName.text = detailData?.name
                        tvDetailDescription.text = detailData?.description
                        Glide.with(root.context)
                            .load(detailData?.photoUrl)
                            .into(ivDetailPhoto)
                    }
                }
            }
        }
    }
}