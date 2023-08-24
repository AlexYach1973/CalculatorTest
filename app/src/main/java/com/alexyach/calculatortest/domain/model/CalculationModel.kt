package com.alexyach.calculatortest.domain.model

const val EMPTY_FIELD = ""

data class CalculationModel(
    var variable1: String = EMPTY_FIELD,
    var variable2: String = EMPTY_FIELD,
    var operation: Operation? = null,
    var result: String = EMPTY_FIELD,
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


