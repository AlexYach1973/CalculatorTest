package com.alexyach.calculatortest.domain.usecases

import android.util.Log
import com.alexyach.calculatortest.domain.Calculator
import com.alexyach.calculatortest.domain.model.CalculationModel
import com.alexyach.calculatortest.domain.model.EMPTY_FIELD
import com.alexyach.calculatortest.domain.model.Operation

class PressEqualBtn(
    calculator: Calculator,
    private val result: (String) -> Unit
) : Command(calculator) {
    private var calcModel = calculator.calculationModel

    override fun execute() {

        if (validateCalculationModel()) {
            Log.d("myLogs", "CalculationModel not Validate")
            result("ERROR")

            // Обнулили
            calculator.calculationModel = CalculationModel()
            return
        }

        val numeric1: Float = calcModel.variable1.toFloat()
        val numeric2: Float = calcModel.variable2.toFloat()

        when (calcModel.operation) {
            Operation.Plus -> {
                calcModel.result = (numeric1 + numeric2).toString()
            }

            Operation.Minus -> {
                calcModel.result = (numeric1 - numeric2).toString()
            }

            Operation.Division -> {
                if (numeric2 != 0F) {
                    calcModel.result = (numeric1 / numeric2).toString()
                } else {
                    calcModel.result = "Деление на ноль"
                }
            }

            Operation.Multiply -> {
                calcModel.result = (numeric1 * numeric2).toString()
            }

            null -> {}
        }
        result(calcModel.result)

        // Обнулили
        calculator.calculationModel = CalculationModel()
    }

    private fun validateCalculationModel(): Boolean {
        return (calcModel.variable1 == EMPTY_FIELD
                || calcModel.variable2 == EMPTY_FIELD
                || calcModel.operation == null)
    }
}