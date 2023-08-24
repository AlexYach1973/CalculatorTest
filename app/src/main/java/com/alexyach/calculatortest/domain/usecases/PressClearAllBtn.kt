package com.alexyach.calculatortest.domain.usecases

import com.alexyach.calculatortest.domain.Calculator
import com.alexyach.calculatortest.domain.model.CalculationModel

class PressClearAllBtn(calculator: Calculator) : Command(calculator) {
    override fun execute() {
        calculator.calculationModel = CalculationModel()
    }
}