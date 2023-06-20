package org.irham3.storyapp.ui.addstory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import org.irham3.storyapp.databinding.ActivityAddStoryBinding

@AndroidEntryPoint
class AddStoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddStoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddStoryBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}