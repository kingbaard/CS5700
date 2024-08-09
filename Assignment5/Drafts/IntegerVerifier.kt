package org.example

class IntegerVerifier {
    lateinit var state: State

    fun verify(string: String): Boolean {
        state = StateIntegerFirstDigit()
        string.chunk(1).forEach {
            state.consumeCharacter(it, this)
        }
        return state is Valid
    }
} 