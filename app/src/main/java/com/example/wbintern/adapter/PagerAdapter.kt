package com.example.wbintern.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.wbintern.screens.ProviderFragment
import com.example.wbintern.screens.ReceiverFragment
import com.example.wbintern.screens.ServiceFragment

class PagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    private val fragments = arrayOf(ProviderFragment(), ReceiverFragment(), ServiceFragment())

    override fun getItemCount() = fragments.size

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> fragments[PROVIDER_FRAGMENT]
            1 -> fragments[RECEIVER_FRAGMENT]
            2 -> fragments[SERVICE_FRAGMENT]
            else -> fragments[PROVIDER_FRAGMENT]
        }
    }


    companion object {
        private const val PROVIDER_FRAGMENT = 0
        private const val RECEIVER_FRAGMENT = 1
        private const val SERVICE_FRAGMENT = 2
    }
}