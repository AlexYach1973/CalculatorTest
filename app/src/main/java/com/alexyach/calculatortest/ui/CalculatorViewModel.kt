package com.alexyach.calculatortest.ui

import android.media.VolumeShaper
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alexyach.calculatortest.model.CalculationModel
import com.alexyach.calculatortest.model.Operation

const val TAG = "myLogs"

class CalculatorViewModel : ViewModel() {

    //    private val _calcModel: MutableLiveData<CalculationModel> = MutableLiveData(CalculationModel())
//    val calcModel: LiveData<CalculationModel> = _calcModel
    var calculationModel = CalculationModel()

    private val _stringForDisplay = MutableLiveData<String>("")
    val stringForDisplay: LiveData<String> = _stringForDisplay

    fun writeVariable(variable: String) {
        if (validateFirstNull(variable)) return

        if (calculationModel.operation == null) {
            calculationModel.variable1 += variable

                _stringForDisplay.value += variable

            Log.d(TAG, "writeVariable var1: ${calculationModel.variable1}")

        } else {
            calculationModel.variable2 += variable
            _stringForDisplay.value += variable

            Log.d(TAG, "writeVariable var2: ${calculationModel.variable2}")
        }

    }

    fun writeOperation(operation: Operation) {
        if (calculationModel.operation == null) {
            calculationModel.operation = operation
            _stringForDisplay.value = ""

//            Log.d(TAG, "writeOperation operation: ${calculationModel.operation}")
        } else {
            Log.d(TAG, "Операция уже назначена")
        }
    }

    fun decimalPoint() {
        if (!validateDecimalPoint()) {
            _stringForDisplay.value += "."

            if (calculationModel.operation == null) {
                calculationModel.variable1 += "."
            } else {
                calculationModel.variable2 += "."
            }

        } else {
            Log.d(TAG, "Точка уже ЕСТЬ!")
        }
    }

    fun equals() {
        if (validateEmptyFields()) return

        val numeric1: Float = calculationModel.variable1.toFloat()
        val numeric2: Float = calculationModel.variable2.toFloat()

        Log.d(TAG, "ToFloat v1= $numeric1")

        when (calculationModel.operation) {
            Operation.Plus -> _stringForDisplay.value = (numeric1 + numeric2).toString()
            Operation.Minus -> _stringForDisplay.value = (numeric1 - numeric2).toString()
            Operation.Division -> {
                if (numeric2 != 0F) {
                    _stringForDisplay.value = (numeric1 / numeric2).toString()
                } else {
                    _stringForDisplay.value = "Деление на ноль"
                    Log.d(TAG, "Деление на ноль")
                }
            }

            Operation.Multiply -> _stringForDisplay.value = (numeric1 * numeric2).toString()
            else -> {}
        }

        // Обнулили
        calculationModel = CalculationModel()
    }

    fun clear() {
        calculationModel = CalculationModel()
        _stringForDisplay.value = ""
    }

    private fun validateFirstNull(num: String): Boolean {
        return (_stringForDisplay.value == "" && num == "0")
    }

    private fun validateDecimalPoint(): Boolean {
        return _stringForDisplay.value!!.contains(".")
    }

    private fun validateEmptyFields(): Boolean {
        return (
                calculationModel.variable1 == "" ||
                        calculationModel.variable2 == "" ||
                        calculationModel.operation == null
                )
    }

}