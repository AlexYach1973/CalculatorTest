package com.alexyach.calculatortest.domain.usecases

import android.util.Log
import com.alexyach.calculatortest.domain.Calculator
import com.alexyach.calculatortest.domain.model.Operation

class PressOperationBtn(
    private val operation: Operation,
    calculator: Calculator
) : Command(calculator) {

    private var calcModel = calculator.calculationModel

    override fun execute() {
        when (operation) {
            Operation.Plus -> calcModel.operation = Operation.Plus
            Operation.Minus -> calcModel.operation = Operation.Minus
            Operation.Division -> calcModel.operation = Operation.Division
            Operation.Multiply -> calcModel.operation = Operation.Multiply
        }
//        Log.d("myLogs", "Calculator, setOperation: ${calcModel}")

        calculator.calculationModel = calcModel
    }

}