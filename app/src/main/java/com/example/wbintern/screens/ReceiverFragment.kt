package com.example.wbintern.screens

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.wbintern.databinding.FragmentReceiverBinding


/**
 * BroadcastReceiver - отображения данных о заряде батареи
 *
 * Считываем данные и отображаем данные в TextView и ProgressBar
 *
 * Подобный функционал использует приложение WhatsApp
 *
 */

class ReceiverFragment : Fragment() {

    private var _binding: FragmentReceiverBinding? = null
    private val binding get() = _binding!!
    private lateinit var batteryReceiver: BroadcastReceiver

    override fun onStart() {
        requireActivity().registerReceiver(
            batteryReceiver,
            IntentFilter(Intent.ACTION_BATTERY_CHANGED)
        )
        super.onStart()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReceiverBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        batteryReceiver = BatteryReceiver()
    }

    override fun onStop() {
        requireActivity().unregisterReceiver(batteryReceiver)
        super.onStop()
    }

    inner class BatteryReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val status = intent?.getIntExtra(BatteryManager.EXTRA_LEVEL, 0)
            binding.textViewStatus.text = "$status%"
            status?.let {
                binding.progressBar.progress = it
            }
        }
    }
}