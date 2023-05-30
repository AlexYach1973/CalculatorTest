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

enum class ErrorMessage{
    IS_OPERAND,
    EMPTY_FIELDS,
    IS_DECIMAL_POINTS,
    DIVISION_FOR_NULL,
    VARIABLE1_IS_EMPTY
}


