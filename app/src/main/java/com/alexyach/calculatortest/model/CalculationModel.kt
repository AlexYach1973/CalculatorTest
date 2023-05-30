package com.alexyach.calculatortest.model

data class CalculationModel(
    var variable1: String = "",
    var variable2: String = "",
    var operation: Operation? = null,
    var result: String = "",
)

enum class Operation{
    Plus,
    Minus,
    Division,
    Multiply
}


