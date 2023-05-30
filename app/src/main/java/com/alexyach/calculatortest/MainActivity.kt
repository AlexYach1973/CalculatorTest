package com.alexyach.calculatortest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alexyach.calculatortest.databinding.ActivityMainBinding
import com.alexyach.calculatortest.ui.CalculatorFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container, CalculatorFragment.newInstance())
                .commit()
        }

    }
}