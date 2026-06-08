package com.example.exaudiapps.Home.Pertemuan13

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.exaudiapps.databinding.ActivityThirteenthBinding
import com.google.android.material.tabs.TabLayoutMediator

class ThirteenthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirteenthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityThirteenthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Setup Toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Pertemuan 13"
            setDisplayHomeAsUpEnabled(true)
        }

        // Inisialisasi Adapter
        val tabsAdapter = ThirteenthTabsAdapter(this)

        // Set adapter ke ViewPager2
        binding.viewPager.adapter = tabsAdapter

        // Hubungkan TabLayout & ViewPager2 menggunakan TabLayoutMediator
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Capture"
                1 -> tab.text = "Scan"
                2 -> tab.text = "QR Code"
            }
        }.attach()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
