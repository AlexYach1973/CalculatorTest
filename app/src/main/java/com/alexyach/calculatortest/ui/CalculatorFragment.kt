package com.alexyach.calculatortest.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.alexyach.calculatortest.R
import com.alexyach.calculatortest.databinding.FragmentCalculatorBinding
import com.alexyach.calculatortest.domain.Calculator
import com.alexyach.calculatortest.domain.model.ErrorMessage
import com.alexyach.calculatortest.domain.model.Operation

class CalculatorFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentCalculatorBinding? = null
    private val binding: FragmentCalculatorBinding get() = _binding!!

    private val calculator = Calculator()

    private val viewModel: CalculatorViewModel by lazy {
        ViewModelProvider(
            this,
            CalculatorViewModel.getViewModelFactory(calculator)
        )[CalculatorViewModel::class.java]
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


    private fun setClickButton() {
        binding.btn0.setOnClickListener(this)
        binding.btn1.setOnClickListener(this)
        binding.btn2.setOnClickListener(this)
        binding.btn3.setOnClickListener(this)
        binding.btn4.setOnClickListener(this)
        binding.btn5.setOnClickListener(this)
        binding.btn6.setOnClickListener(this)
        binding.btn7.setOnClickListener(this)
        binding.btn8.setOnClickListener(this)
        binding.btn9.setOnClickListener(this)
        binding.btnDiv.setOnClickListener(this)
        binding.btnPlus.setOnClickListener(this)
        binding.btnMinus.setOnClickListener(this)
        binding.btnMult.setOnClickListener(this)
        binding.btnDot.setOnClickListener(this)
        binding.btnClear.setOnClickListener(this)
        binding.btnEquals.setOnClickListener(this)
    }
    override fun onClick(v: View?) {
        with(binding) {
            when(v!!){
                /** Numbers */
                btn0 -> viewModel.writeVariable("0")
                btn1 -> viewModel.writeVariable("1")
                btn2 -> viewModel.writeVariable("2")
                btn3 -> viewModel.writeVariable("3")
                btn4 -> viewModel.writeVariable("4")
                btn5 -> viewModel.writeVariable("5")
                btn6 -> viewModel.writeVariable("6")
                btn7 -> viewModel.writeVariable("7")
                btn8 -> viewModel.writeVariable("8")
                btn9 -> viewModel.writeVariable("9")
                /** Operation */
                btnPlus -> viewModel.setOperation(Operation.Plus)
                btnMinus -> viewModel.setOperation(Operation.Minus)
                btnDiv -> viewModel.setOperation(Operation.Division)
                btnMult -> viewModel.setOperation(Operation.Multiply)
                /** decimalPoint */
                btnDot -> viewModel.decimalPoint()
                /** Equals */
                btnEquals ->  viewModel.equals()
                btnClear -> viewModel.clearAll()
                else -> {}
            }
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