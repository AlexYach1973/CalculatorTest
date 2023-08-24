package com.alexyach.calculatortest.domain.usecases

import com.alexyach.calculatortest.domain.Calculator

abstract class Command(protected val calculator: Calculator) {
    abstract fun execute()
}