package org.example

import IntegerState
import IntegerStateFirstDigit
import IntegerStateValid

class IntegerVerifier {
    lateinit var state: IntegerState

    fun verify(string: String): Boolean {
        state = IntegerStateFirstDigit()
        string.chunked(1).forEach {
            state.consumeCharacter(it, this)
        }
        return state is IntegerStateValid
    }
} 