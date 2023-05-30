package com.alexyach.calculatortest.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.alexyach.calculatortest.R
import com.alexyach.calculatortest.databinding.FragmentCalculatorBinding
import com.alexyach.calculatortest.model.ErrorMessage
import com.alexyach.calculatortest.model.Operation

class CalculatorFragment : Fragment() {

    private var _binding: FragmentCalculatorBinding? = null
    private val binding: FragmentCalculatorBinding get() = _binding!!

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

        viewModel.stringForDisplay.observe(viewLifecycleOwner) {
            binding.textViewDisplay.text = it
        }

        viewModel.stringForStory.observe(viewLifecycleOwner) {
            binding.textViewStory.text = it
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) {
            showToastError(it)
        }

    }

    private fun setClickButton() {

        with(binding) {

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
                viewModel.setOperation(Operation.Plus)
            }
            btnMinus.setOnClickListener {
                viewModel.setOperation(Operation.Minus)
            }
            btnDiv.setOnClickListener {
                viewModel.setOperation(Operation.Division)
            }
            btnMult.setOnClickListener {
                viewModel.setOperation(Operation.Multiply)
            }

            /** decimalPoint */
            btnDot.setOnClickListener {
                viewModel.decimalPoint()
            }

            /** Equals */
            btnEquals.setOnClickListener {
                viewModel.equals()
            }

            /** Clear all fields */
            btnClear.setOnClickListener {
                viewModel.clear()
            }

            // Долгое нажатие - очистить вместе с историей
            btnClear.setOnLongClickListener {
                viewModel.clearAll()
                true
            }

        }
    }

    private fun showToastError(message: ErrorMessage) {
        when (message) {
            ErrorMessage.DIVISION_FOR_NULL -> {
                showToast(resources.getString(R.string.division_for_null))
            }

            ErrorMessage.EMPTY_FIELDS -> {
                showToast(resources.getString(R.string.empty_fields))
            }

            ErrorMessage.IS_OPERAND -> {
                showToast(resources.getString(R.string.is_operand))
            }

            ErrorMessage.IS_DECIMAL_POINTS -> {
                showToast(resources.getString(R.string.is_decimal_point))
            }

            ErrorMessage.VARIABLE1_IS_EMPTY ->
                showToast(resources.getString(R.string.variable1_is_empty))
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        fun newInstance() = CalculatorFragment()
    }
}