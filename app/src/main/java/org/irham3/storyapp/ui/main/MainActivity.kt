package org.irham3.storyapp.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import org.irham3.storyapp.R
import org.irham3.storyapp.databinding.ActivityMainBinding
import org.irham3.storyapp.ui.adapter.LoadingStateAdapter
import org.irham3.storyapp.ui.adapter.StoryAdapter
import org.irham3.storyapp.ui.addstory.AddStoryActivity
import org.irham3.storyapp.ui.auth.AuthActivity
import org.irham3.storyapp.ui.map.MapsActivity

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel.getSession().observe(this) { session ->
            if(!session) {
                startActivity(Intent(this@MainActivity, AuthActivity::class.java))
                finish()
            } else {
                mainViewModel.getAuthToken().observe(this) { authToken ->
                    showRecyclerView(authToken)
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.action_map -> {
                startActivity(Intent(this, MapsActivity::class.java))
                true
            }
            R.id.action_logout -> {
                mainViewModel.logout()
                Toast.makeText(this, "Anda telah keluar", Toast.LENGTH_SHORT).show()
                true
            }

            R.id.action_tambah_cerita -> {
                startActivity(Intent(this@MainActivity, AddStoryActivity::class.java))
                true
            }

            else -> false
        }
    }

    private fun showRecyclerView(token: String) {
        binding.rvStory.layoutManager = LinearLayoutManager(this@MainActivity)
        val storyAdapter = StoryAdapter()
        binding.rvStory.adapter = storyAdapter.withLoadStateFooter(
            footer = LoadingStateAdapter {
                storyAdapter.retry()
            }
        )
        mainViewModel.getAllStories(token).observe(this) { pagingData ->
            storyAdapter.submitData(lifecycle, pagingData)
        }
    }
}