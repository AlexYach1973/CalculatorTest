package com.alexyach.calculatortest.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alexyach.calculatortest.domain.Calculator
import com.alexyach.calculatortest.domain.model.ErrorMessage
import com.alexyach.calculatortest.domain.model.Operation

const val TAG = "myLogs"

class CalculatorViewModel(
    private val calculator: Calculator
) : ViewModel() {

    private var stringFromEditText = ""
    private var isNewCalculate = true

    private val _stringForDisplay = MutableLiveData("")
    val stringForDisplay: LiveData<String> = _stringForDisplay

    private val _stringForStory = MutableLiveData("")
    val stringForStory: LiveData<String> = _stringForStory

    private var newCalculation = false
    val errorMessage = MutableLiveData<ErrorMessage>()

    fun writeVariable(variable: String) {
        if (isNewCalculate) {
            isNewCalculate = false
            _stringForDisplay.value = ""
        }


        stringFromEditText += variable
        _stringForDisplay.value += variable

        validateFirstNull()
    }

    fun setOperation(operation: Operation) {
        calculator.pressNumericBtn(stringFromEditText)

        stringFromEditText = ""
        _stringForDisplay.value += when (operation) {
            Operation.Multiply -> " * "
            Operation.Plus -> " + "
            Operation.Minus -> " - "
            Operation.Division -> " / "
        }

        calculator.pressOperationBtn(operation)
    }

    fun decimalPoint() {
        if (!validateDecimalPoint()) {
            _stringForDisplay.value += "."
//            _stringForStory.value += "."

            stringFromEditText += "."

        } else {
            errorMessage.value = ErrorMessage.IS_DECIMAL_POINTS
            Log.d(TAG, "Точка уже ЕСТЬ!")
        }
    }

    fun equals() {
        calculator.pressNumericBtn(stringFromEditText)

        stringFromEditText = ""

        calculator.pressEqualBtn { result ->
            _stringForDisplay.value = result
        }
        isNewCalculate = true
    }

    fun clearAll() {
        calculator.pressClearAllBtn()
        _stringForDisplay.value = ""
    }

    private fun validateDecimalPoint(): Boolean {
        return stringFromEditText.contains(".")
    }

    /** Убираем первые нули в переменных */
    private fun validateFirstNull() {
        if (_stringForDisplay.value?.length!! == 2) {
            val charArray = _stringForDisplay.value?.toCharArray()

            if (charArray!![0] == '0' &&
                charArray[1] == '0'
            ) {
                _stringForDisplay.value = ""
            } else if (charArray[0] == '0' &&
                charArray[1] != '.'
            ) {
                _stringForDisplay.value = charArray[1].toString()
            }
        }
    }

    private fun newCalculation() {
        if (newCalculation) {
            _stringForDisplay.value = ""
            _stringForStory.value += "\n"
            newCalculation = false
        }
    }


    companion object {
        fun getViewModelFactory(calculator: Calculator): ViewModelProvider.Factory {
            val factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    if (modelClass.isAssignableFrom(CalculatorViewModel::class.java)) {
                        return CalculatorViewModel(calculator) as T
                    } else {
                        throw IllegalArgumentException("Unknown ViewModel class")
                    }
                }
            }
            return factory
        }
    }
}