package com.alexyach.calculatortest.domain.usecases

import android.util.Log
import com.alexyach.calculatortest.domain.Calculator
import com.alexyach.calculatortest.domain.model.EMPTY_FIELD

class PressNumericBtn(
    private val variable: String,
    calculator: Calculator
) : Command(calculator) {

    private var calcModel = calculator.calculationModel

    override fun execute() {
        if (calcModel.variable1 == EMPTY_FIELD) {
            calcModel.variable1 = variable
        } else {
            calcModel.variable2 = variable
        }

        calculator.calculationModel = calcModel

        Log.d("myLogs", "Calculator, writeVariable: ${calcModel}")
    }
}