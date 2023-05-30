package com.alexyach.calculatortest.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.alexyach.calculatortest.R
import com.alexyach.calculatortest.databinding.FragmentCalculatorBinding
import com.alexyach.calculatortest.model.Operation

class CalculatorFragment : Fragment() {

    private var _binding: FragmentCalculatorBinding? = null
    private val binding: FragmentCalculatorBinding get() = _binding!!

    private val buttonsNumId = intArrayOf(R.id.btn_0, R.id.btn_1,
        R.id.btn_2, R.id.btn_3, R.id.btn_4, R.id.btn_5, R.id.btn_6,
        R.id.btn_7, R.id.btn_8, R.id.btn_9)



    private val viewModel: CalculatorViewModel by lazy {
        ViewModelProvider(this)[CalculatorViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCalculatorBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setClickButton()
        observeData()
    }

    private fun observeData() {

        viewModel.stringForDisplay.observe(viewLifecycleOwner){
            binding.textViewDisplay.text = it
        }

    }

    private fun setClickButton(){

        with(binding){

            /** Numbers */
            btn0.setOnClickListener {
                viewModel.writeVariable("0")
            }
            btn1.setOnClickListener {
                viewModel.writeVariable("1")
            }
            btn2.setOnClickListener {
                viewModel.writeVariable("2")
            }
            btn3.setOnClickListener {
                viewModel.writeVariable("3")
            }
            btn4.setOnClickListener {
                viewModel.writeVariable("4")
            }
            btn5.setOnClickListener {
                viewModel.writeVariable("5")
            }
            btn6.setOnClickListener {
                viewModel.writeVariable("6")
            }
            btn7.setOnClickListener {
                viewModel.writeVariable("7")
            }
            btn8.setOnClickListener {
                viewModel.writeVariable("8")
            }
            btn9.setOnClickListener {
                viewModel.writeVariable("9")
            }

            /** Operation */
            btnPlus.setOnClickListener {
                viewModel.writeOperation(Operation.Plus)
            }
            btnMinus.setOnClickListener {
                viewModel.writeOperation(Operation.Minus)
            }
            btnDiv.setOnClickListener {
                viewModel.writeOperation(Operation.Division)
            }
            btnMult.setOnClickListener {
                viewModel.writeOperation(Operation.Multiply)
            }

            /** decimalPoint */
            btnDot.setOnClickListener {
                viewModel.decimalPoint()
            }

            /** Equals */
            btnEquals.setOnClickListener {
                viewModel.equals()
            }

            /** Clear */
            btnClear.setOnClickListener {
                viewModel.clear()
            }

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        fun newInstance() = CalculatorFragment()
    }
}