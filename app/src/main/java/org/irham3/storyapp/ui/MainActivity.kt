package org.irham3.storyapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.navArgs
import dagger.hilt.android.AndroidEntryPoint
import org.irham3.storyapp.databinding.ActivityMainBinding
import org.irham3.storyapp.ui.auth.AuthActivity

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_TOKEN = "extra_token"
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val token = intent.getStringExtra(EXTRA_TOKEN)

        if(token == null) {
            startActivity(Intent(this@MainActivity, AuthActivity::class.java))
            finish()
        }

    }
}