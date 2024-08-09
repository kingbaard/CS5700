package org.example

class BinaryVerifier {
    lateinit var state: State

    fun verify(string: String): Boolean {
        state = StateBinaryFirstDigit()
        string.chunk(1).forEach {
            state.consumeCharacter(it, this)
        }
        if (string.chunk(1)[string.size - 1] != "1") {
            state = StateBinaryInvalid
        }
        return state is Valid
    }
} 