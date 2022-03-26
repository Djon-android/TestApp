package com.example.testapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.testapp.R
import com.example.testapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, CharacterListFragment.newInstance())
                .commit()
        }
    }
}