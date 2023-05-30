package com.alexyach.calculatortest.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alexyach.calculatortest.model.CalculationModel
import com.alexyach.calculatortest.model.ErrorMessage
import com.alexyach.calculatortest.model.Operation

const val TAG = "myLogs"

class CalculatorViewModel : ViewModel() {

    var calculationModel = CalculationModel()

    private val _stringForDisplay = MutableLiveData("")
    val stringForDisplay: LiveData<String> = _stringForDisplay

    private val _stringForStory = MutableLiveData("")
    val stringForStory: LiveData<String> = _stringForStory

    private var newCalculation = false
    val errorMessage = MutableLiveData<ErrorMessage>()

    fun writeVariable(variable: String) {

        newCalculation()
        validateFirstNull()

        if (calculationModel.operation == null) {
            calculationModel.variable1 += variable

            _stringForDisplay.value += variable
            _stringForStory.value += variable
        } else {
            calculationModel.variable2 += variable
            _stringForDisplay.value += variable
            _stringForStory.value += variable
        }

    }

    fun setOperation(operation: Operation) {

        if (calculationModel.variable1 == "") {
            errorMessage.value = ErrorMessage.VARIABLE1_IS_EMPTY
            return
        }

        if (calculationModel.operation == null) {
            calculationModel.operation = operation
            _stringForDisplay.value = ""

            when (operation) {
                Operation.Plus -> _stringForStory.value += " + "
                Operation.Minus -> _stringForStory.value += " - "
                Operation.Division -> _stringForStory.value += " / "
                Operation.Multiply -> _stringForStory.value += " * "
            }

        } else {
            errorMessage.value = ErrorMessage.IS_OPERAND
        }
    }

    fun decimalPoint() {
        newCalculation()
        if (!validateDecimalPoint()) {
            _stringForDisplay.value += "."
            _stringForStory.value += "."

            if (calculationModel.operation == null) {
                calculationModel.variable1 += "."
            } else {
                calculationModel.variable2 += "."
            }

        } else {
            errorMessage.value = ErrorMessage.IS_DECIMAL_POINTS
            Log.d(TAG, "Точка уже ЕСТЬ!")
        }
    }

    fun equals() {
        if (validateEmptyFields()) {
            errorMessage.value = ErrorMessage.EMPTY_FIELDS
            return
        }

        val numeric1: Float = calculationModel.variable1.toFloat()
        val numeric2: Float = calculationModel.variable2.toFloat()

        when (calculationModel.operation) {
            Operation.Plus -> {
                _stringForDisplay.value = (numeric1 + numeric2).toString()
                _stringForStory.value += "=  ${(numeric1 + numeric2)}"
            }

            Operation.Minus -> {
                _stringForDisplay.value = (numeric1 - numeric2).toString()
                _stringForStory.value += "= ${(numeric1 - numeric2)}"
            }

            Operation.Division -> {
                if (numeric2 != 0F) {
                    _stringForDisplay.value = (numeric1 / numeric2).toString()
                    _stringForStory.value += "= ${(numeric1 / numeric2)}"
                } else {
                    _stringForDisplay.value = "Деление на ноль"
                    _stringForStory.value += "Деление на ноль"
                    errorMessage.value = ErrorMessage.DIVISION_FOR_NULL
                }
            }

            Operation.Multiply -> {
                _stringForDisplay.value = (numeric1 * numeric2).toString()
                _stringForStory.value += "= ${(numeric1 * numeric2)}"
            }

            else -> {}
        }

        newCalculation = true

        // Обнулили
        calculationModel = CalculationModel()
    }

    fun clear() {
        calculationModel = CalculationModel()
        _stringForDisplay.value = ""
    }

    fun clearAll() {
        calculationModel = CalculationModel()
        _stringForDisplay.value = ""
        _stringForStory.value = ""
    }

    private fun validateDecimalPoint(): Boolean {
        return _stringForDisplay.value!!.contains(".")
    }

    /** Убираем первые нули в переменных */
    private fun validateFirstNull() {

        while (_stringForDisplay.value!!.isNotEmpty()
            && _stringForDisplay.value!!.first() == '0'
        ) {
            _stringForDisplay.value =
                _stringForDisplay.value!!.substring(1)
        }
    }

    private fun newCalculation(){
        if(newCalculation){
            _stringForDisplay.value = ""
            _stringForStory.value += "\n"
            newCalculation = false
        }
    }

    private fun validateEmptyFields(): Boolean {
        return (
                calculationModel.variable1 == "" ||
                        calculationModel.variable2 == "" ||
                        calculationModel.operation == null
                )
    }

}