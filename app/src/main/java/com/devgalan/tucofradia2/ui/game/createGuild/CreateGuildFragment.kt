package com.devgalan.tucofradia2.ui.game.createGuild

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.devgalan.tucofradia2.R
import com.devgalan.tucofradia2.databinding.FragmentCreateGuildBinding

class CreateGuildFragment : Fragment() {

    private lateinit var binding: FragmentCreateGuildBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreateGuildBinding.inflate(inflater, container, false)

        initUI()

        initListeners()

        return binding.root
    }

    private fun initListeners() {
        val etCuote = binding.etCuote
        etCuote.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                val str = s.toString()
                if (str.isNotEmpty()) {
                    val num = str.toInt()
                    if (num < 1) {
                        etCuote.setText("1")
                        etCuote.setSelection(etCuote.text.length) // move cursor to end
                    } else if (num > 100) {
                        etCuote.setText("100")
                        etCuote.setSelection(etCuote.text.length) // move cursor to end
                    }
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // No need to do anything here
            }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                // No need to do anything here
            }
        })
    }

    private fun initUI() {
        
    }
}