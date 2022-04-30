package com.example.wbintern.screens

import android.content.ContentValues
import android.database.Cursor
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.wbintern.R
import com.example.wbintern.provider.MyContentProvider
import com.example.wbintern.databinding.FragmentProviderBinding

/**
 * ContentProvider - считывает данные с БД.
 *  В классе MyHelper создал БД с одной табличкой, и заполнил данными
 *
 *  Написал Provider с помощью которого мы можем считывать, изменять и удалять данные в созданной БД.
 *
 *  Экран содержит 2 текстовых поля и 6 кнопок для редактирования и отображения данных.
 *
 *  Популярные пиложения которые используют подобный функционал.
 *
 *  Я думаю что подобный функционал используют приложения от компании яндекс.
 *  Так как при авторизации в одном из приложений,
 *  все приложения автоматически проводят авторизацию пользователя.
 */

class ProviderFragment : Fragment() {

    private var _binding: FragmentProviderBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProviderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val resolver = requireActivity().contentResolver.query(
            MyContentProvider.CONTENT_URI,
            arrayOf(MyContentProvider.ID, MyContentProvider.CITY, MyContentProvider.TEMPERATURE),
            null,
            null,
            null
        )

        buttonClick(resolver)
    }

    private fun buttonClick(resolver: Cursor?) {
        binding.buttonNext.setOnClickListener {
            if (resolver?.moveToNext()!!) {
                binding.editTextTextCity.setText(resolver.getString(FIRST_COLUMN))
                binding.editTextTextTemperature.setText(resolver.getString(SECOND_COLUMN))
            }
        }

        binding.buttonPrevious.setOnClickListener {
            if (resolver?.moveToPrevious()!!) {
                binding.editTextTextCity.setText(resolver.getString(FIRST_COLUMN))
                binding.editTextTextTemperature.setText(resolver.getString(SECOND_COLUMN))
            }
        }

        binding.buttonClear.setOnClickListener {
            binding.editTextTextCity.setText("")
            binding.editTextTextTemperature.setText("")
            binding.editTextTextCity.requestFocus()
        }

        binding.buttonInsert.setOnClickListener {
            val contentValues = ContentValues()
            contentValues.apply {
                put(MyContentProvider.CITY, binding.editTextTextCity.text.toString())
                put(MyContentProvider.TEMPERATURE, binding.editTextTextTemperature.text.toString())
            }
            requireActivity().contentResolver.insert(MyContentProvider.CONTENT_URI, contentValues)
            resolver?.requery()
        }

        binding.buttonUpdate.setOnClickListener {
            val contentValues = ContentValues()
            contentValues.put(
                MyContentProvider.TEMPERATURE,
                binding.editTextTextTemperature.text.toString()
            )
            requireActivity().contentResolver.update(
                MyContentProvider.CONTENT_URI,
                contentValues,
                getString(R.string.city_row),
                arrayOf(binding.editTextTextCity.text.toString())
            )
            resolver?.requery()
        }

        binding.buttonDelete.setOnClickListener {
            requireActivity().contentResolver.delete(
                MyContentProvider.CONTENT_URI,
                getString(R.string.city_row),
                arrayOf(binding.editTextTextCity.text.toString())
            )
            resolver?.requery()
        }
    }

    companion object {
        private const val FIRST_COLUMN = 1
        private const val SECOND_COLUMN = 2
    }
}