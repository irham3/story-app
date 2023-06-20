package org.irham3.storyapp.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import org.irham3.storyapp.R
import org.irham3.storyapp.data.Result
import org.irham3.storyapp.databinding.ActivityMainBinding
import org.irham3.storyapp.ui.adapter.StoryAdapter
import org.irham3.storyapp.ui.auth.AuthActivity

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel.getAuthToken().observe(this) { authToken ->
            if(authToken.isEmpty()) {
                startActivity(Intent(this@MainActivity, AuthActivity::class.java))
                finish()
            } else
                showRecyclerView(authToken)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.action_logout -> {
                mainViewModel.logout()
                true
            }
            else -> false
        }
    }

    private fun showRecyclerView(token: String) {
        mainViewModel.getAllStories("Bearer $token").observe(this) { result ->
            when(result) {
                is Result.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Result.Success -> {
                    binding.progressBar.visibility = View.GONE
                    val storyList = result.data

                    if (storyList != null) {
                        binding.rvStory.apply {
                            visibility = View.VISIBLE
                            layoutManager = LinearLayoutManager(this@MainActivity)
                            adapter = StoryAdapter(storyList)
                        }
                    }
                }
                is Result.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, result.message.toString(), Toast.LENGTH_LONG).show()
                }
                else -> {}
            }
        }
    }
}