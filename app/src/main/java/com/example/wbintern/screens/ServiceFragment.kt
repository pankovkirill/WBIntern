package com.example.wbintern.screens

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.wbintern.R
import com.example.wbintern.databinding.FragmentServiceBinding
import com.example.wbintern.service.MyService

/**
 * Service - проигрывание музыки в бэкграунде
 *
 * Популярные приложения которые используют подобный функционал YouTube, VK, Яндекс Музыка
 */

class ServiceFragment : Fragment() {

    private var isPlaying = false
    private var _binding: FragmentServiceBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentServiceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.textView.setOnClickListener {
            if (!isPlaying) {
                playAudio()
                binding.textView.text = getString(R.string.stop)
                isPlaying = true
            } else {
                stopAudio()
                binding.textView.text = getString(R.string.play)
                isPlaying = false
            }
        }
    }

    private fun stopAudio() {
        try {
            activity?.stopService(Intent(context, MyService::class.java))
        } catch (e: SecurityException) {
            Toast.makeText(context, "Error: " + e.message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun playAudio() {
        try {
            activity?.startService(
                Intent(context, MyService::class.java)
            )
        } catch (e: SecurityException) {
            Toast.makeText(context, "Error: " + e.message, Toast.LENGTH_SHORT).show()
        }
    }
}