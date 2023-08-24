package com.alexyach.calculatortest.domain

import com.alexyach.calculatortest.domain.model.CalculationModel
import com.alexyach.calculatortest.domain.model.Operation
import com.alexyach.calculatortest.domain.usecases.PressClearAllBtn
import com.alexyach.calculatortest.domain.usecases.PressEqualBtn
import com.alexyach.calculatortest.domain.usecases.PressNumericBtn
import com.alexyach.calculatortest.domain.usecases.PressOperationBtn

class Calculator {
    var calculationModel = CalculationModel()
    private val calculator: Calculator = this

    fun pressNumericBtn(variable: String) {
        PressNumericBtn(variable, calculator).execute()
    }

    fun pressOperationBtn(operation: Operation) {
        PressOperationBtn(operation, calculator).execute()
    }

    fun pressEqualBtn(result: (String) -> Unit) {
        PressEqualBtn(calculator, result).execute()
    }

    fun pressClearAllBtn() {
        PressClearAllBtn(calculator).execute()
    }

}