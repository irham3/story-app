package org.irham3.storyapp.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import org.irham3.storyapp.R
import org.irham3.storyapp.databinding.ActivityMainBinding
import org.irham3.storyapp.ui.auth.AuthActivity

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.mainToolbar.inflateMenu(R.menu.main_menu)

        mainViewModel.getAuthToken().observe(this) { authToken ->
            if(authToken.isEmpty()) {
                startActivity(Intent(this@MainActivity, AuthActivity::class.java))
                finish()
            }
        }

        binding.mainToolbar.setOnMenuItemClickListener {menuItem ->
            when(menuItem.itemId) {
                R.id.action_logout -> {
                    mainViewModel.logout()
                    true
                }
                else -> false
            }
        }
    }

}