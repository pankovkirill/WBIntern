package com.example.wbintern.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.wbintern.R
import com.example.wbintern.adapter.PagerAdapter
import com.example.wbintern.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

/**
 * MainActivity - контейнер для фрагментов.
 *
 * Содержит ViewPager2, с помощью которого мы можем переключатся между фрагментами
 * и TabLayout для отображения названия экранов
 *
 * Написал Adapter для управления навигацией
 *
 * В MainActivity инициализировали адаптер и присвоили заголовки для TabLayout
 *
 * Популярные приложения, которые используют подобный функционал - Тинькофф Инвестиции
 */

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initial()
    }

    private fun initial() {
        binding.viewPager.adapter = PagerAdapter(this)
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                FRAGMENT_PROVIDER -> tab.text = getString(R.string.provider)
                FRAGMENT_RECEIVER -> tab.text = getString(R.string.receiver)
                FRAGMENT_SERVICE -> tab.text = getString(R.string.service)
                else -> tab.text = getString(R.string.provider)
            }
        }.attach()
    }

    companion object {
        private const val FRAGMENT_PROVIDER = 0
        private const val FRAGMENT_RECEIVER = 1
        private const val FRAGMENT_SERVICE = 2
    }
}